package shaobig.caxmean.quiz.packager.hint;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public interface HintPackager {
    String getHint(FullCard card);
}
