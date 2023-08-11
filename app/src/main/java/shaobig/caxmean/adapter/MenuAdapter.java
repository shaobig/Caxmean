package shaobig.caxmean.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shaobig.caxmean.R;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionClickListener;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionDeleteClickListener;
import shaobig.caxmean.adapter.listeners.collection.OnCardCollectionEditClickListener;
import shaobig.caxmean.adapter.viewholder.MenuViewHolder;
import shaobig.caxmean.database.entities.CardCollection;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context context;
    private List<CardCollection> cardCollections;

    private OnCardCollectionClickListener cardCollectionClickListener;
    private OnCardCollectionEditClickListener cardCollectionEditClickListener;
    private OnCardCollectionDeleteClickListener cardCollectionDeleteClickListener;

    private MenuAdapter(Context context, List<CardCollection> cardCollections) {
        this.context = context;
        this.cardCollections = cardCollections;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.card_collection_element, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        CardCollection collection = getCardCollections().get(position);

        String name = collection.getName();
        String created = collection.getCreated();
        String edited = collection.getEdited();
        Integer priority = collection.getPriority();

        holder.setNameView(name);
        holder.setCreatedView(created);
        holder.setEditedView(edited);
        holder.setPriorityView(priority);

        holder.itemView.setOnClickListener(v -> getCardCollectionClickListener().onCardCollectionClick(collection));

        holder.getEditButton().setOnClickListener(v -> getCardCollectionEditClickListener().onCardCollectionEditClick(collection));
        holder.getDeleteButton().setOnClickListener(v -> getCardCollectionDeleteClickListener().onCardCollectionDelete(collection));
    }

    @Override
    public int getItemCount() {
        return getCardCollections().size();
    }

    public Context getContext() {
        return context;
    }

    public List<CardCollection> getCardCollections() {
        return cardCollections;
    }

    public OnCardCollectionClickListener getCardCollectionClickListener() {
        return cardCollectionClickListener;
    }

    public OnCardCollectionEditClickListener getCardCollectionEditClickListener() {
        return cardCollectionEditClickListener;
    }

    public OnCardCollectionDeleteClickListener getCardCollectionDeleteClickListener() {
        return cardCollectionDeleteClickListener;
    }

    public void setCardCollectionClickListener(OnCardCollectionClickListener cardCollectionClickListener) {
        this.cardCollectionClickListener = cardCollectionClickListener;
    }

    public void setCardCollectionEditClickListener(OnCardCollectionEditClickListener cardCollectionEditClickListener) {
        this.cardCollectionEditClickListener = cardCollectionEditClickListener;
    }

    public void setCardCollectionDeleteClickListener(OnCardCollectionDeleteClickListener cardCollectionDeleteClickListener) {
        this.cardCollectionDeleteClickListener = cardCollectionDeleteClickListener;
    }

    public static class Builder {
        private MenuAdapter adapter;

        public Builder(Context context, List<CardCollection> cardCollections) {
            this.adapter = new MenuAdapter(context, cardCollections);
        }

        public Builder setOnCardCollectionClickListener(OnCardCollectionClickListener listener) {
            this.adapter.setCardCollectionClickListener(listener);
            return this;
        }

        public Builder setOnCardCollectionEditClickListener(OnCardCollectionEditClickListener cardCollectionEditClickListener) {
            this.adapter.setCardCollectionEditClickListener(cardCollectionEditClickListener);
            return this;
        }

        public Builder setOnCardCollectionDeleteClickListener(OnCardCollectionDeleteClickListener cardCollectionDeleteClickListener) {
            this.adapter.cardCollectionDeleteClickListener = cardCollectionDeleteClickListener;
            return this;
        }

        public MenuAdapter getAdapter() {
            return adapter;
        }
    }
}
