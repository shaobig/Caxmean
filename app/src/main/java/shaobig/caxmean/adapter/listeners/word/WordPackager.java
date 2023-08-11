package shaobig.caxmean.adapter.listeners.word;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public interface WordPackager {
    String getNextWord(FullCard card);
}
