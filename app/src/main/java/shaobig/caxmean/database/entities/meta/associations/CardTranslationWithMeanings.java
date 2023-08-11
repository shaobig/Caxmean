package shaobig.caxmean.database.entities.meta.associations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import shaobig.caxmean.database.entities.CardMeaning;
import shaobig.caxmean.database.entities.CardSemantic;
import shaobig.caxmean.database.entities.CardTranslation;

public class CardTranslationWithMeanings {
    @Embedded
    private CardTranslation translation;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(value = CardSemantic.class)
    )
    private List<CardMeaning> meanings;

    public CardTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(CardTranslation translation) {
        this.translation = translation;
    }

    public List<CardMeaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<CardMeaning> meanings) {
        this.meanings = meanings;
    }
}
