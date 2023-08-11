package shaobig.caxmean.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shaobig.caxmean.R;
import shaobig.caxmean.adapter.listeners.card.OnCardClickListener;
import shaobig.caxmean.adapter.listeners.card.OnCardDeleteButtonClickListener;
import shaobig.caxmean.adapter.listeners.card.OnCardEditButtonClickListener;
import shaobig.caxmean.adapter.listeners.filter.CardCollectionAdapterFilter;
import shaobig.caxmean.adapter.listeners.word.FirstMeaningWordPackager;
import shaobig.caxmean.adapter.listeners.word.TranslationSwitchWordPackagerFactory;
import shaobig.caxmean.adapter.listeners.word.WordPackager;
import shaobig.caxmean.adapter.viewholder.CardCollectionViewHolder;
import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class CardCollectionAdapter extends RecyclerView.Adapter<CardCollectionViewHolder> implements Filterable {

    private Context context;
    private List<FullCard> cards;

    private OnCardClickListener onCardClickListener;

    private OnCardEditButtonClickListener onCardEditButtonClickListener;
    private OnCardDeleteButtonClickListener onCardDeleteButtonClickListener;

    private Filter cardCollectionFilter;

    private WordPackager wordPackager;

    private CardCollectionAdapter(Context context, List<FullCard> cards) {
        this.context = context;
        this.cards = cards;

        this.cardCollectionFilter = new CardCollectionAdapterFilter(cards);

        this.wordPackager = new FirstMeaningWordPackager();
    }

    private CardCollectionAdapter(Context context, List<FullCard> cards, boolean isActive) {
        this.context = context;
        this.cards = cards;

        this.cardCollectionFilter = new CardCollectionAdapterFilter(cards);

        this.wordPackager = new TranslationSwitchWordPackagerFactory(isActive).getNextWordPackager();
    }

    @NonNull
    @Override
    public CardCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.card_element, parent, false);

        return new CardCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCollectionViewHolder holder, int position) {
        FullCard card = getCards().get(position);

        Integer number = position + 1;
        String meaning = getWordPackager().getNextWord(card);

        holder.setNumberView(number);
        holder.setNameView(meaning);

        holder.itemView.setOnClickListener(v -> getOnCardClickListener().onCardClick(card));

        holder.getEditCardButton().setOnClickListener(v -> getOnCardEditButtonClickListener().onCardEditButtonClick(card));
        holder.getDeleteCardButton().setOnClickListener(v -> getOnCardDeleteButtonClickListener().onDeleteCardButtonClick(card));
    }

    @Override
    public int getItemCount() {
        return getCards().size();
    }

    @Override
    public Filter getFilter() { return getCardCollectionFilter(); }

    public Context getContext() {
        return context;
    }

    public List<FullCard> getCards() {
        return cards;
    }

    public OnCardClickListener getOnCardClickListener() {
        return onCardClickListener;
    }

    public WordPackager getWordPackager() {
        return wordPackager;
    }

    private void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public OnCardEditButtonClickListener getOnCardEditButtonClickListener() {
        return onCardEditButtonClickListener;
    }

    public OnCardDeleteButtonClickListener getOnCardDeleteButtonClickListener() {
        return onCardDeleteButtonClickListener;
    }

    private void setOnCardEditButtonClickListener(OnCardEditButtonClickListener onCardEditButtonClickListener) {
        this.onCardEditButtonClickListener = onCardEditButtonClickListener;
    }

    private void setOnCardDeleteButtonClickListener(OnCardDeleteButtonClickListener onCardDeleteButtonClickListener) {
        this.onCardDeleteButtonClickListener = onCardDeleteButtonClickListener;
    }

    public Filter getCardCollectionFilter() {
        return cardCollectionFilter;
    }

    public void setCards(List<FullCard> cards) {
        this.cards = cards;
    }

    public static class Builder {
        private CardCollectionAdapter adapter;

        public Builder(Context context, List<FullCard> cards, boolean isActive) {
            this.adapter = new CardCollectionAdapter(context, cards, isActive);
        }

        public Builder setOnCardClickListener(OnCardClickListener onCardClickListener) {
            this.adapter.setOnCardClickListener(onCardClickListener);
            return this;
        }

        public Builder setOnCardEditButtonClickListener(OnCardEditButtonClickListener onCardEditButtonClickListener) {
            this.adapter.setOnCardEditButtonClickListener(onCardEditButtonClickListener);
            return this;
        }

        public Builder setOnCardDeleteButtonClickListener(OnCardDeleteButtonClickListener onCardDeleteButtonClickListener) {
            this.adapter.setOnCardDeleteButtonClickListener(onCardDeleteButtonClickListener);
            return this;
        }

        public CardCollectionAdapter getAdapter() {
            return adapter;
        }
    }
}
