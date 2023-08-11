package shaobig.caxmean.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_semantic",
        foreignKeys = {
            @ForeignKey(
                    entity = CardMeaning.class,
                    childColumns = "meaning_id",
                    parentColumns = "id"
            ),
            @ForeignKey(
                    entity = CardTranslation.class,
                    childColumns = "translation_id",
                    parentColumns = "id"
            )
    }
)
public class CardSemantic implements IdEntity<Long> {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "meaning_id")
    @NonNull
    private Long meaningId;
    @NonNull
    @ColumnInfo(name = "translation_id")
    private Long translationId;

    public CardSemantic(Long id, @NonNull Long meaningId, @NonNull Long translationId) {
        this.id = id;
        this.meaningId = meaningId;
        this.translationId = translationId;
    }

    @Ignore
    public CardSemantic(@NonNull Long meaningId, @NonNull Long translationId) {
        this.meaningId = meaningId;
        this.translationId = translationId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Long getMeaningId() { return meaningId; }

    public void setMeaningId(@NonNull Long meaningId) {
        this.meaningId = meaningId;
    }

    @NonNull
    public Long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(@NonNull Long translationId) {
        this.translationId = translationId;
    }
}
