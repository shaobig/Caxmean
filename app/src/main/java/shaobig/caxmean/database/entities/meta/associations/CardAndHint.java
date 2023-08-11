package shaobig.caxmean.database.entities.meta.associations;

import androidx.room.Embedded;
import androidx.room.Relation;

import shaobig.caxmean.database.entities.Card;
import shaobig.caxmean.database.entities.CardHint;

public class CardAndHint {
    @Embedded
    private Card card;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    private CardHint hint;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardHint getHint() {
        return hint;
    }

    public void setHint(CardHint hint) {
        this.hint = hint;
    }
}
