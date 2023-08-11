package shaobig.caxmean.database.entities.meta.dto;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FullCard implements Serializable {

    private static final String DEFAULT_STRING_VALUE = "";

    private static final Integer MEANINGS_COUNT = 5;
    private static final Integer TRANSLATIONS_COUNT = 5;

    @ColumnInfo(name = "card_id")
    private Long cardId;
    @ColumnInfo(name = "collection_id")
    private Long collectionId;

    @ColumnInfo(name = "meaning_id")
    private Long meaningId;
    @ColumnInfo(name = "first_meaning")
    private String firstMeaning;
    @ColumnInfo(name = "second_meaning")
    private String secondMeaning;
    @ColumnInfo(name = "third_meaning")
    private String thirdMeaning;
    @ColumnInfo(name = "fourth_meaning")
    private String fourthMeaning;
    @ColumnInfo(name = "fifth_meaning")
    private String fifthMeaning;

    @ColumnInfo(name = "translation_id")
    private Long translationId;
    @ColumnInfo(name = "first_translation")
    private String firstTranslation;
    @ColumnInfo(name = "second_translation")
    private String secondTranslation;
    @ColumnInfo(name = "third_translation")
    private String thirdTranslation;
    @ColumnInfo(name = "fourth_translation")
    private String fourthTranslation;
    @ColumnInfo(name = "fifth_translation")
    private String fifthTranslation;

    @ColumnInfo(name = "semantic_id")
    private Long semanticId;

    @ColumnInfo(name = "hint_id")
    private Long hintId;
    @ColumnInfo(name = "native_hint")
    private String nativeHint;
    @ColumnInfo(name = "foreign_hint")
    private String foreignHint;

    @ColumnInfo(name = "transcription")
    private String transcription;

    @Ignore
    private List<String> meanings;
    @Ignore
    private List<String> translations;

    public FullCard(Long cardId, Long collectionId,
                    Long meaningId, String firstMeaning, String secondMeaning, String thirdMeaning, String fourthMeaning, String fifthMeaning,
                    Long translationId, String firstTranslation, String secondTranslation, String thirdTranslation, String fourthTranslation, String fifthTranslation,
                    Long hintId, String nativeHint, String foreignHint,
                    String transcription) {
        this.cardId = cardId;
        this.collectionId = collectionId;
        this.meaningId = meaningId;
        this.firstMeaning = firstMeaning;
        this.secondMeaning = secondMeaning;
        this.thirdMeaning = thirdMeaning;
        this.fourthMeaning = fourthMeaning;
        this.fifthMeaning = fifthMeaning;
        this.translationId = translationId;
        this.firstTranslation = firstTranslation;
        this.secondTranslation = secondTranslation;
        this.thirdTranslation = thirdTranslation;
        this.fourthTranslation = fourthTranslation;
        this.fifthTranslation = fifthTranslation;
        this.hintId = hintId;
        this.nativeHint = nativeHint;
        this.foreignHint = foreignHint;
        this.transcription = transcription;

        this.meanings = getDefaultMeanings();
        this.translations = getDefaultTranslations();
    }

    @Ignore
    private FullCard() {
        this.firstMeaning = DEFAULT_STRING_VALUE;
        this.secondMeaning = DEFAULT_STRING_VALUE;
        this.thirdMeaning = DEFAULT_STRING_VALUE;
        this.fourthMeaning = DEFAULT_STRING_VALUE;
        this.fifthMeaning = DEFAULT_STRING_VALUE;

        this.firstTranslation = DEFAULT_STRING_VALUE;
        this.secondTranslation = DEFAULT_STRING_VALUE;
        this.thirdTranslation = DEFAULT_STRING_VALUE;
        this.fourthTranslation = DEFAULT_STRING_VALUE;
        this.fifthTranslation = DEFAULT_STRING_VALUE;

        this.nativeHint = DEFAULT_STRING_VALUE;
        this.foreignHint = DEFAULT_STRING_VALUE;

        this.transcription = DEFAULT_STRING_VALUE;

        this.meanings = getDefaultMeanings();
        this.translations = getDefaultTranslations();
    }

    private List<String> getDefaultMeanings() {
        return Stream.of(
                firstMeaning,
                secondMeaning,
                thirdMeaning,
                fourthMeaning,
                fifthMeaning
        ).collect(Collectors.toList());
    }

    private List<String> getDefaultTranslations() {
        return Stream.of(
                firstTranslation,
                secondTranslation,
                thirdTranslation,
                fourthTranslation,
                fifthTranslation
        ).collect(Collectors.toList());
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getMeaningId() {
        return meaningId;
    }

    public void setMeaningId(Long meaningId) {
        this.meaningId = meaningId;
    }

    public String getFirstMeaning() {
        return firstMeaning;
    }

    public void setFirstMeaning(String firstMeaning) {
        this.firstMeaning = firstMeaning;
    }

    public String getSecondMeaning() {
        return secondMeaning;
    }

    public void setSecondMeaning(String secondMeaning) {
        this.secondMeaning = secondMeaning;
    }

    public String getThirdMeaning() {
        return thirdMeaning;
    }

    public void setThirdMeaning(String thirdMeaning) {
        this.thirdMeaning = thirdMeaning;
    }

    public String getFourthMeaning() {
        return fourthMeaning;
    }

    public void setFourthMeaning(String fourthMeaning) {
        this.fourthMeaning = fourthMeaning;
    }

    public String getFifthMeaning() {
        return fifthMeaning;
    }

    public void setFifthMeaning(String fifthMeaning) {
        this.fifthMeaning = fifthMeaning;
    }

    public Long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(Long translationId) {
        this.translationId = translationId;
    }

    public String getFirstTranslation() {
        return firstTranslation;
    }

    public void setFirstTranslation(String firstTranslation) {
        this.firstTranslation = firstTranslation;
    }

    public String getSecondTranslation() {
        return secondTranslation;
    }

    public void setSecondTranslation(String secondTranslation) {
        this.secondTranslation = secondTranslation;
    }

    public String getThirdTranslation() {
        return thirdTranslation;
    }

    public void setThirdTranslation(String thirdTranslation) {
        this.thirdTranslation = thirdTranslation;
    }

    public String getFourthTranslation() {
        return fourthTranslation;
    }

    public void setFourthTranslation(String fourthTranslation) {
        this.fourthTranslation = fourthTranslation;
    }

    public String getFifthTranslation() {
        return fifthTranslation;
    }

    public void setFifthTranslation(String fifthTranslation) {
        this.fifthTranslation = fifthTranslation;
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

    public String getNativeHint() {
        return nativeHint;
    }

    public void setNativeHint(String nativeHint) {
        this.nativeHint = nativeHint;
    }

    public String getForeignHint() {
        return foreignHint;
    }

    public void setForeignHint(String foreignHint) {
        this.foreignHint = foreignHint;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public List<String> getMeanings() {
        return meanings;
    }

    private void setMeanings(List<String> meanings) {
        if (meanings != null) {
            if (meanings.size() <= MEANINGS_COUNT) {
                this.meanings = meanings;
            }
            else {
                String message = "The meanings size = "
                        .concat(String.valueOf(meanings.size()))
                        .concat(" and should be less than ")
                        .concat(String.valueOf(MEANINGS_COUNT));

                throw new IllegalArgumentException(message);
            }
        }
        else {
            String message = "The meanings list has no reference to an object";
            throw new NullPointerException(message);
        }
    }

    public List<String> getTranslations() {
        return translations;
    }

    private void setTranslations(List<String> translations) {
        if (translations != null) {
            if (translations.size() <= TRANSLATIONS_COUNT) {
                this.translations = translations;
            }
            else {
                String message = "The translations size = "
                        .concat(String.valueOf(translations.size()))
                        .concat(" and should be less than ")
                        .concat(String.valueOf(TRANSLATIONS_COUNT));

                throw new IllegalArgumentException(message);
            }
        }
        else {
            String message = "The translations list has no reference to an object";
            throw new NullPointerException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullCard fullCard = (FullCard) o;
        return firstMeaning.equals(fullCard.firstMeaning) &&
                Objects.equals(secondMeaning, fullCard.secondMeaning) &&
                Objects.equals(thirdMeaning, fullCard.thirdMeaning) &&
                Objects.equals(fourthMeaning, fullCard.fourthMeaning) &&
                Objects.equals(fifthMeaning, fullCard.fifthMeaning) &&
                firstTranslation.equals(fullCard.firstTranslation) &&
                Objects.equals(secondTranslation, fullCard.secondTranslation) &&
                Objects.equals(thirdTranslation, fullCard.thirdTranslation) &&
                Objects.equals(fourthTranslation, fullCard.fourthTranslation) &&
                Objects.equals(fifthTranslation, fullCard.fifthTranslation) &&
                Objects.equals(nativeHint, fullCard.nativeHint) &&
                Objects.equals(foreignHint, fullCard.foreignHint) &&
                Objects.equals(transcription, fullCard.transcription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstMeaning, secondMeaning, thirdMeaning, fourthMeaning, fifthMeaning,
                firstTranslation, secondTranslation, thirdTranslation, fourthTranslation, fifthTranslation,
                nativeHint, foreignHint,
                transcription
        );
    }

    public static class Builder {
        private FullCard card;

        public Builder() {
            this.card = new FullCard();
        }

        public Builder setCardId(Long id) {
            card.setCardId(id);
            return this;
        }

        public Builder setCollectionId(Long id) {
            card.setCollectionId(id);
            return this;
        }

        public Builder setFirstMeaning(String meaning) {
            card.setFirstMeaning(meaning);
            return this;
        }

        public Builder setSecondMeaning(String meaning) {
            card.setSecondMeaning(meaning);
            return this;
        }

        public Builder setThirdMeaning(String meaning) {
            card.setThirdMeaning(meaning);
            return this;
        }

        public Builder setFourthMeaning(String meaning) {
            card.setFourthMeaning(meaning);
            return this;
        }

        public Builder setFifthMeaning(String meaning) {
            card.setFifthMeaning(meaning);
            return this;
        }

        public Builder setFirstTranslation(String translation) {
            card.setFirstTranslation(translation);
            return this;
        }

        public Builder setSecondTranslation(String translation) {
            card.setSecondTranslation(translation);
            return this;
        }

        public Builder setThirdTranslation(String translation) {
            card.setThirdTranslation(translation);
            return this;
        }

        public Builder setFourthTranslation(String translation) {
            card.setFourthTranslation(translation);
            return this;
        }

        public Builder setFifthTranslation(String translation) {
            card.setFifthTranslation(translation);
            return this;
        }

        public Builder setNativeHint(String hint) {
            card.setNativeHint(hint);
            return this;
        }

        public Builder setForeignHint(String hint) {
            card.setForeignHint(hint);
            return this;
        }

        public Builder setTranscription(String transcription) {
            card.setTranscription(transcription);
            return this;
        }

        public Builder setMeanings(List<String> meanings) {
            card.setFirstMeaning(meanings.get(0));
            card.setSecondMeaning(meanings.get(1));
            card.setThirdMeaning(meanings.get(2));
            card.setFourthMeaning(meanings.get(3));
            card.setFifthMeaning(meanings.get(4));

            card.setMeanings(meanings);

            return this;
        }

        public Builder setTranslations(List<String> translations) {
            card.setFirstTranslation(translations.get(0));
            card.setSecondTranslation(translations.get(1));
            card.setThirdTranslation(translations.get(2));
            card.setFourthTranslation(translations.get(3));
            card.setFifthTranslation(translations.get(4));

            card.setTranslations(translations);

            return this;
        }

        public FullCard getCard() {
            return card;
        }
    }
}
