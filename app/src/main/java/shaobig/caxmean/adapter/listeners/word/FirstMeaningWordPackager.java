package shaobig.caxmean.adapter.listeners.word;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class FirstMeaningWordPackager implements WordPackager {
    @Override
    public String getNextWord(FullCard card) {
        return card.getFirstMeaning();
    }
}
