package shaobig.caxmean.adapter.listeners.filter;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class CardCollectionAdapterFilter extends Filter {

    private List<FullCard> originalCards;
    private List<FullCard> activeCards;

    public CardCollectionAdapterFilter(List<FullCard> cards) {
        this.originalCards = new ArrayList<>(cards);
        this.activeCards = cards;
    }

    @Override
    protected FilterResults performFiltering(CharSequence word) {
        FilterResults results = new FilterResults();
        results.values = getOriginalCards().stream()
                .filter(card ->
                        card.getMeanings().stream()
                                .filter(meaning -> !meaning.isEmpty())
                                .map(meaning -> meaning.trim().toLowerCase())
                                .anyMatch(match -> match.contains(word.toString().trim().toLowerCase())) ||
                        card.getTranslations().stream()
                                .filter(translation -> !translation.isEmpty())
                                .map(translation -> translation.trim().toLowerCase())
                                .anyMatch(match -> match.contains(word.toString().trim().toLowerCase()))
                )
                .collect(Collectors.toList());

        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void publishResults(CharSequence constraint, FilterResults results) {
        getActiveCards().clear();
        getActiveCards().addAll((Collection<? extends FullCard>) results.values);

        Log.d("cards_size", String.valueOf(getActiveCards().size()));
    }

    public List<FullCard> getOriginalCards() {
        return originalCards;
    }

    public List<FullCard> getActiveCards() {
        return activeCards;
    }

}
