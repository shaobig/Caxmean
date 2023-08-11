package shaobig.caxmean.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shaobig.caxmean.R;
import shaobig.caxmean.adapter.MenuAdapter;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionClickListener;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionDeleteClickListener;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionEditClickListener;
import shaobig.caxmean.database.CardDatabase;
import shaobig.caxmean.database.entities.CardCollection;
import shaobig.caxmean.dialogs.AddCardCollectionDialog;
import shaobig.caxmean.dialogs.listeners.OnAddCardCollectionConfirmListener;
import shaobig.caxmean.dialogs.listeners.OnDeleteCardCollectionConfirmListener;
import shaobig.caxmean.dialogs.listeners.OnEditCardCollectionConfirmListener;
import shaobig.caxmean.listeners.adapter.AdapterItemController;
import shaobig.caxmean.listeners.adapter.EntityAdapterUploader;

import static shaobig.caxmean.dialogs.DeleteCardCollectionDialog.getDeleteCardCollectionDialog;
import static shaobig.caxmean.dialogs.EditCardCollectionDialog.getEditCardCollectionDialog;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_ID;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_NAME;
import static shaobig.caxmean.meta.ActivityKeyKeeper.EXECUTOR_THREADS_COUNT;
import static shaobig.caxmean.meta.DialogTagKeeper.ADD_CARD_COLLECTION_DIALOG_TAG;
import static shaobig.caxmean.meta.DialogTagKeeper.DELETE_CARD_COLLECTION_DIALOG_TAG;
import static shaobig.caxmean.meta.DialogTagKeeper.EDIT_CARD_COLLECTION_DIALOG_TAG;

public class MenuActivity extends AppCompatActivity implements
        EntityAdapterUploader,
        OnCardCollectionClickListener,
        OnAddCardCollectionConfirmListener,
        AdapterItemController<CardCollection>,
        OnCardCollectionEditClickListener,
        OnEditCardCollectionConfirmListener,
        OnCardCollectionDeleteClickListener,
        OnDeleteCardCollectionConfirmListener {

    private RecyclerView cardCollectionView;
    private FloatingActionButton addCollectionButton;
    private FloatingActionButton deleteCollectionButton;

    private CardDatabase database;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardCollectionView = findViewById(R.id.main_view);
        addCollectionButton = findViewById(R.id.add_collection_floating_action_button);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        addCollectionButton.setOnClickListener(v -> {
            DialogFragment addCardCollectionDialog = new AddCardCollectionDialog();
            addCardCollectionDialog.show(getSupportFragmentManager(), ADD_CARD_COLLECTION_DIALOG_TAG);
        });

        database = CardDatabase.restoreDatabase(this);
        executor = Executors.newFixedThreadPool(EXECUTOR_THREADS_COUNT);

        uploadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void uploadData() {
        getExecutor().execute(() -> {
            List<CardCollection> cardCollections = getDatabase().getCardCollectionDao().readAll();

            runOnUiThread(() -> {
                MenuAdapter adapter = (MenuAdapter) getCardCollectionView().getAdapter();

                if (adapter == null) {
                    adapter = new MenuAdapter.Builder(this, cardCollections)
                            .setOnCardCollectionClickListener(this)
                            .setOnCardCollectionEditClickListener(this)
                            .setOnCardCollectionDeleteClickListener(this)
                            .getAdapter();

                    getCardCollectionView().setLayoutManager(new LinearLayoutManager(this));
                    getCardCollectionView().setAdapter(adapter);
                }
            });
        });
    }

    @Override
    public void addItem(CardCollection collection) {
        MenuAdapter adapter = (MenuAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            adapter.getCardCollections().add(collection);
            adapter.notifyItemInserted(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void updateItem(CardCollection collection) {
        MenuAdapter adapter = (MenuAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            int index = adapter.getCardCollections().stream()
                    .filter(f -> f.getId().equals(collection.getId()))
                    .map(m -> adapter.getCardCollections().indexOf(m))
                    .findFirst()
                    .orElse(-1);

            adapter.getCardCollections().set(index, collection);
            adapter.notifyItemChanged(adapter.getItemCount() - 1);
        }
    }

    @Override
    public void deleteItem(CardCollection collection) {
        MenuAdapter adapter = (MenuAdapter) getCardCollectionView().getAdapter();

        if (adapter != null) {
            int index = adapter.getCardCollections().stream()
                    .filter(f -> f.getId().equals(collection.getId()))
                    .map(m -> adapter.getCardCollections().indexOf(m))
                    .findFirst()
                    .orElse(-1);

            adapter.getCardCollections().remove(index);
            adapter.notifyItemRemoved(index);
        }
    }

    @Override
    public void onCardCollectionClick(CardCollection collection) {
        Intent intent = new Intent(this, CardCollectionActivity.class);

        intent.putExtra(CARD_COLLECTION_ID, collection.getId());
        intent.putExtra(CARD_COLLECTION_NAME, collection.getName());

        startActivity(intent);
    }

    @Override
    public void onAddCardCollectionConfirm(CardCollection collection) {
        getExecutor().execute(() -> {
            Long collectionId = getDatabase().getCardCollectionDao().create(collection);
            collection.setId(collectionId);

            onCardCollectionClick(collection);
        });

        addItem(collection);
    }

    @Override
    public void onCardCollectionEditClick(CardCollection collection) {
        DialogFragment editCardCollectionDialog = getEditCardCollectionDialog(collection);
        editCardCollectionDialog.show(getSupportFragmentManager(), EDIT_CARD_COLLECTION_DIALOG_TAG);
    }

    @Override
    public void onEditCardCollectionConfirm(CardCollection collection) {
        getExecutor().execute(() -> {
            getDatabase().getCardCollectionDao().update(collection);
            runOnUiThread(() -> updateItem(collection));
        });
    }

    @Override
    public void onCardCollectionDelete(CardCollection collection) {
        DialogFragment dialog = getDeleteCardCollectionDialog(collection);
        dialog.show(getSupportFragmentManager(), DELETE_CARD_COLLECTION_DIALOG_TAG);
    }

    @Override
    public void onDeleteCardCollectionConfirm(CardCollection collection, String name) {
        if (collection.getName().equals(name)) {
            getExecutor().execute(() -> {
                getDatabase().getCardDao().deleteAllCards(collection.getId());
                getDatabase().getCardCollectionDao().delete(collection.getId());

                runOnUiThread(() -> deleteItem(collection));
            });
        } else {
            Toast.makeText(this, "Имена не совпадают - отказ в удалении коллекции", Toast.LENGTH_SHORT).show();
        }
    }

    public RecyclerView getCardCollectionView() {
        return cardCollectionView;
    }

    public FloatingActionButton getAddCollectionButton() {
        return addCollectionButton;
    }

    public CardDatabase getDatabase() {
        return database;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

}