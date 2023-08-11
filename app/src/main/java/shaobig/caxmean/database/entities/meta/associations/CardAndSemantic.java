package shaobig.caxmean.database.entities.meta.associations;

import androidx.room.Embedded;
import androidx.room.Relation;

import shaobig.caxmean.database.entities.Card;
import shaobig.caxmean.database.entities.CardSemantic;

public class CardAndSemantic {
    @Embedded
    private Card card;
    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    private CardSemantic semantic;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardSemantic getSemantic() {
        return semantic;
    }

    public void setSemantic(CardSemantic semantic) {
        this.semantic = semantic;
    }
}
