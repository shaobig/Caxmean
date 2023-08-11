package shaobig.caxmean.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shaobig.caxmean.R;
import shaobig.caxmean.adapter.CardCollectionAdapter;
import shaobig.caxmean.adapter.listeners.card.OnCardClickListener;
import shaobig.caxmean.adapter.listeners.card.OnCardDeleteButtonClickListener;
import shaobig.caxmean.adapter.listeners.card.OnCardEditButtonClickListener;
import shaobig.caxmean.database.CardDatabase;
import shaobig.caxmean.database.entities.Card;
import shaobig.caxmean.database.entities.CardHint;
import shaobig.caxmean.database.entities.CardMeaning;
import shaobig.caxmean.database.entities.CardSemantic;
import shaobig.caxmean.database.entities.CardTranslation;
import shaobig.caxmean.database.entities.meta.dto.FullCard;
import shaobig.caxmean.dialogs.AddCardDialog;
import shaobig.caxmean.dialogs.EditCardDialog;
import shaobig.caxmean.dialogs.ShowCardDialog;
import shaobig.caxmean.dialogs.listeners.OnAddCardConfirmListener;
import shaobig.caxmean.dialogs.listeners.OnEditCardConfirmListener;
import shaobig.caxmean.listeners.activity.TimeUpdater;
import shaobig.caxmean.listeners.adapter.AdapterItemController;
import shaobig.caxmean.listeners.adapter.EntityAdapterUploader;

import static shaobig.caxmean.database.entities.CardCollection.getCurrentTime;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_ID;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_NAME;
import static shaobig.caxmean.meta.ActivityKeyKeeper.EXECUTOR_THREADS_COUNT;
import static shaobig.caxmean.meta.ActivityKeyKeeper.TRANSLATION_SWITCH_KEY;
import static shaobig.caxmean.meta.DialogTagKeeper.ADD_CARD_DIALOG_TAG;
import static shaobig.caxmean.meta.DialogTagKeeper.EDIT_CARD_DIALOG_TAG;
import static shaobig.caxmean.meta.DialogTagKeeper.SHOW_CARD_DIALOG_TAG;

public class CardCollectionActivity extends AppCompatActivity implements
        EntityAdapterUploader,
        OnCardClickListener,
        OnAddCardConfirmListener,
        OnCardEditButtonClickListener,
        OnEditCardConfirmListener,
        OnCardDeleteButtonClickListener,
        AdapterItemController<FullCard>,
        TimeUpdater {

    private RecyclerView cardCollectionView;

    private FloatingActionButton addCardButton;
    private FloatingActionButton startQuizButton;

    private Switch switchTranslation;

    private CardDatabase database;
    private ExecutorService executor;

    private Long collectionId;
    private String collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);

        cardCollectionView = findViewById(R.id.card_collection_view);

        addCardButton = findViewById(R.id.add_card_floating_action_button);
        startQuizButton = findViewById(R.id.start_quiz_floating_action_button);

        database = CardDatabase.getDatabase(this);
        executor = Executors.newFixedThreadPool(EXECUTOR_THREADS_COUNT);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        addCardButton.setOnClickListener(v -> {
            DialogFragment addCardDialog = AddCardDialog.getAddCardDialog();
            addCardDialog.show(getSupportFragmentManager(), ADD_CARD_DIALOG_TAG);
        });
        startQuizButton.setOnClickListener(v -> {
            try {
                CardCollectionAdapter adapter = (CardCollectionAdapter) getCardCollectionView().getAdapter();
                if (adapter != null) {
                    if (adapter.getCards().isEmpty()) {
                        Toast.makeText(this, "Пожалуйста, заполните список для активации викторины", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(this, QuizActivity.class);

                        intent.putExtra(CARD_COLLECTION_ID, getCollectionId());
                        intent.putExtra(CARD_COLLECTION_NAME, getCollectionName());

                        intent.putExtra(TRANSLATION_SWITCH_KEY, getSwitchTranslation().isChecked());

                        startActivity(intent);
                    }
                }
            }
            catch (ClassCastException e) {
                e.printStackTrace();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            collectionId = bundle.getLong(CARD_COLLECTION_ID);
            collectionName = bundle.getString(CARD_COLLECTION_NAME);

            setTitle(collectionName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_collection, menu);

        switchTranslation = (Switch) menu.findItem(R.id.card_collection_translation_switch_bar).getActionView();
        switchTranslation.setOnCheckedChangeListener((buttonView, isChecked) -> uploadData());

        SearchView searchView = (SearchView) menu.findItem(R.id.card_collection_search_bar).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String predicate) {
                if (predicate.isEmpty()) {
                    uploadData();
                }
                CardCollectionAdapter adapter = (CardCollectionAdapter) getCardCollectionView().getAdapter();
                if (adapter != null) {
                    adapter.getFilter().filter(predicate);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        uploadData();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MenuActivity.class));
    }

    @Override
    public void uploadData() {
        getExecutor().execute(() -> {
            List<FullCard> cards = getDatabase().getFullCardDao().readAllCollectionCards(getCollectionId());
            runOnUiThread(() -> {
                CardCollectionAdapter adapter = new CardCollectionAdapter.Builder(this, cards, getSwitchTranslation().isChecked())
                        .setOnCardClickListener(this)
                        .setOnCardEditButtonClickListener(this)
                        .setOnCardDeleteButtonClickListener(this)
                        .getAdapter();
                getCardCollectionView().setLayoutManager(new LinearLayoutManager(this));
                getCardCollectionView().setAdapter(adapter);
            });
        });
    }

    @Override
    public void addItem(FullCard card) {
        CardCollectionAdapter adapter = (CardCollectionAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            adapter.getCards().add(card);
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
        }

        updateTime();
    }

    @Override
    public void updateItem(FullCard card) {
        CardCollectionAdapter adapter = (CardCollectionAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            int index = adapter.getCards().stream()
                    .filter(f -> f.getCardId().equals(card.getCardId()))
                    .map(m -> adapter.getCards().indexOf(m))
                    .findFirst()
                    .orElse(-1);

            adapter.getCards().set(index, card);
            adapter.notifyItemChanged(index);
        }

        updateTime();
    }

    @Override
    public void deleteItem(FullCard card) {
        CardCollectionAdapter adapter = (CardCollectionAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            int index = adapter.getCards().stream()
                    .filter(f -> f.getCardId().equals(card.getCardId()))
                    .map(m -> adapter.getCards().indexOf(m))
                    .findFirst()
                    .orElse(-1);

            adapter.getCards().remove(index);
            adapter.notifyItemRemoved(index);
        }

        updateTime();
    }

    @Override
    public void onCardClick(FullCard card) {
        DialogFragment showDialog = ShowCardDialog.createDialog(card);
        showDialog.show(getSupportFragmentManager(), SHOW_CARD_DIALOG_TAG);
    }

    @Override
    public void onAddCardConfirm(FullCard card) {
        getExecutor().execute(() -> {
            CardMeaning meaning = new CardMeaning.Builder()
                    .setFirst(card.getFirstMeaning())
                    .setSecond(card.getSecondMeaning())
                    .setThird(card.getThirdMeaning())
                    .setFourth(card.getFourthMeaning())
                    .setFifth(card.getFifthMeaning())
                    .getMeaning();

            CardTranslation translation = new CardTranslation.Builder()
                    .setFirst(card.getFirstTranslation())
                    .setSecond(card.getSecondTranslation())
                    .setThird(card.getThirdTranslation())
                    .setFourth(card.getFourthTranslation())
                    .setFifth(card.getFifthTranslation())
                    .getTranslation();

            Long meaningId = getDatabase().getCardMeaningDao().create(meaning);
            Long translationId = getDatabase().getCardTranslationDao().create(translation);

            CardSemantic semantic = new CardSemantic(meaningId, translationId);
            Long semanticId = getDatabase().getCardSemanticDao().create(semantic);

            CardHint hint = new CardHint(card.getNativeHint(), card.getForeignHint());
            Long hintId = getDatabase().getCardHintDao().create(hint);

            String transcription = card.getTranscription();

            Card newCard = new Card.Builder()
                    .setCollectionId(getCollectionId())
                    .setSemanticId(semanticId)
                    .setHintId(hintId)
                    .setTranscription(transcription)
                    .getCard();

            Long id = getDatabase().getCardDao().create(newCard);
            card.setCardId(id);

            Log.d("card id after creation", String.valueOf(card.getCardId()));

            runOnUiThread(() -> addItem(card));
        });
    }

    @Override
    public void onCardEditButtonClick(FullCard card) {
        DialogFragment editCardDialog = EditCardDialog.getDialog(card);
        editCardDialog.show(getSupportFragmentManager(), EDIT_CARD_DIALOG_TAG);
    }

    @Override
    public void onEditCardConfirm(FullCard oldCard, FullCard newCard) {
        if (!oldCard.equals(newCard)) {
            getExecutor().execute(() -> {
                newCard.setCardId(oldCard.getCardId());
                newCard.setCollectionId(oldCard.getCollectionId());
                newCard.setMeaningId(oldCard.getMeaningId());
                newCard.setTranslationId(oldCard.getTranslationId());
                newCard.setHintId(oldCard.getHintId());

                getDatabase().getFullCardDao().update(newCard);
                runOnUiThread(() -> updateItem(newCard));
            });
        }
    }

    @Override
    public void onDeleteCardButtonClick(FullCard card) {
        getExecutor().execute(() -> {
            getDatabase().getCardDao().delete(card.getCardId());
            runOnUiThread(() -> deleteItem(card));
        });
    }

    @Override
    public void updateTime() {
        getExecutor().execute(() -> {
            getDatabase().getCardCollectionDao().updateTime(getCollectionId(), getCurrentTime());
        });
    }

    public RecyclerView getCardCollectionView() {
        return cardCollectionView;
    }

    public CardDatabase getDatabase() {
        return database;
    }

    public void setDatabase(CardDatabase database) {
        this.database = database;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public Switch getSwitchTranslation() {
        return switchTranslation;
    }

    public String getCollectionName() {
        return collectionName;
    }
}