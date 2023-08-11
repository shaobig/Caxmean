package shaobig.caxmean.quiz.packager.hint;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class NativeHintPackager implements HintPackager {
    @Override
    public String getHint(FullCard card) {
        return card.getNativeHint();
    }
}
