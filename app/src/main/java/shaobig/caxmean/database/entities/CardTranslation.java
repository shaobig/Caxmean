package shaobig.caxmean.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(tableName = "card_translation")
public class CardTranslation implements IdEntity<Long> {

    private static final String DEFAULT_TRANSLATION = "";

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "first")
    private String first;
    @ColumnInfo(name = "second")
    private String second;
    @ColumnInfo(name = "third")
    private String third;
    @ColumnInfo(name = "fourth")
    private String fourth;
    @ColumnInfo(name = "fifth")
    private String fifth;

    @Ignore
    private List<String> translations;

    public CardTranslation(Long id, String first, String second, String third, String fourth, String fifth) {
        this.id = id;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    private CardTranslation() {
        this.first = DEFAULT_TRANSLATION;
        this.second = DEFAULT_TRANSLATION;
        this.third = DEFAULT_TRANSLATION;
        this.fourth = DEFAULT_TRANSLATION;
        this.fifth = DEFAULT_TRANSLATION;

        List<String> translations = Stream.of(
                getFirst(), getSecond(), getThird(), getFourth(), getFifth()
        ).collect(Collectors.toList());

        this.setTranslations(translations);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public List<String> getTranslations() {
        return translations;
    }

    private void setTranslations(List<String> translations) {
        if (translations != null && !translations.isEmpty()) {
            this.translations = translations;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public static class Builder {

        private CardTranslation translation;

        public Builder() {
            translation = new CardTranslation();
        }

        public Builder setId(Long id) {
            this.translation.setId(id);
            return this;
        }

        public Builder setFirst(String meaning) {
            this.translation.setFirst(meaning);
            return this;
        }

        public Builder setSecond(String meaning) {
            this.translation.setSecond(meaning);
            return this;
        }

        public Builder setThird(String meaning) {
            this.translation.setThird(meaning);
            return this;
        }

        public Builder setFourth(String meaning) {
            this.translation.setFourth(meaning);
            return this;
        }

        public Builder setFifth(String meaning) {
            this.translation.setFifth(meaning);
            return this;
        }

        public CardTranslation getTranslation() {
            return translation;
        }
    }
}
