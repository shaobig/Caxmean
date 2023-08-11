package shaobig.caxmean.database.entities.meta.associations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import shaobig.caxmean.database.entities.CardMeaning;
import shaobig.caxmean.database.entities.CardSemantic;
import shaobig.caxmean.database.entities.CardTranslation;

public class CardMeaningWithTranslations {
    @Embedded
    private CardMeaning meaning;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(value = CardSemantic.class)
    )
    private List<CardTranslation> translations;

    public CardMeaning getMeaning() {
        return meaning;
    }

    public void setMeaning(CardMeaning meaning) {
        this.meaning = meaning;
    }

    public List<CardTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<CardTranslation> translations) {
        this.translations = translations;
    }
}
