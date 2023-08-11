package shaobig.caxmean.adapter.listeners.word;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class FirstTranslationWordPackager implements WordPackager {
    @Override
    public String getNextWord(FullCard card) {
        return card.getFirstTranslation();
    }
}
