package shaobig.caxmean.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "card", foreignKeys = {
        @ForeignKey(entity = CardCollection.class, childColumns = "collection_id", parentColumns = "id"),
        @ForeignKey(entity = CardSemantic.class, childColumns = "semantic_id", parentColumns = "id"),
        @ForeignKey(entity = CardHint.class, childColumns = "hint_id", parentColumns = "id")
})
public class Card implements IdEntity<Long> {

    private static final String DEFAULT_TRANSCRIPTION = "";

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "collection_id")
    private Long collectionId;
    @ColumnInfo(name = "semantic_id")
    private Long semanticId;
    @ColumnInfo(name = "hint_id")
    private Long hintId;
    @ColumnInfo(name = "transcription")
    private String transcription;

    public Card(Long id, Long collectionId, Long semanticId, Long hintId, String transcription) {
        this.id = id;
        this.collectionId = collectionId;
        this.semanticId = semanticId;
        this.hintId = hintId;
        this.transcription = transcription;
    }

    @Ignore
    public Card(Long collectionId, Long semanticId) {
        this.collectionId = collectionId;
        this.semanticId = semanticId;

        this.transcription = DEFAULT_TRANSCRIPTION;
    }

    @Ignore
    public Card(Long collectionId, Long semanticId, Long hintId) {
        this.collectionId = collectionId;
        this.semanticId = semanticId;
        this.hintId = hintId;

        this.transcription = DEFAULT_TRANSCRIPTION;
    }

    @Ignore
    public Card(Long id, Long collectionId, Long semanticId, Long hintId) {
        this(collectionId, semanticId, hintId);
        this.id = id;
    }

    private Card() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getSemanticId() {
        return semanticId;
    }

    public void setSemanticId(Long semanticId) {
        this.semanticId = semanticId;
    }

    public Long getHintId() {
        return hintId;
    }

    public void setHintId(Long hintId) {
        this.hintId = hintId;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id) &&
                collectionId.equals(card.collectionId) &&
                semanticId.equals(card.semanticId) &&
                hintId.equals(card.hintId) &&
                Objects.equals(transcription, card.transcription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collectionId, semanticId, hintId, transcription);
    }

    public static class Builder {

        private Card card;

        public Builder() {
            card = new Card();
        }

        public Builder setId(Long id) {
            card.setId(id);
            return this;
        }

        public Builder setCollectionId(Long id) {
            card.setCollectionId(id);
            return this;
        }

        public Builder setSemanticId(Long id) {
            card.setSemanticId(id);
            return this;
        }

        public Builder setHintId(Long id) {
            card.setHintId(id);
            return this;
        }

        public Builder setTranscription(String transcription) {
            card.setTranscription(transcription);
            return this;
        }

        public Card getCard() {
            return card;
        }
    }
}
