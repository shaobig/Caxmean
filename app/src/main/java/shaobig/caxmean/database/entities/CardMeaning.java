package shaobig.caxmean.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(tableName = "card_meaning")
public class CardMeaning implements IdEntity<Long> {

    private static final Integer MEANINGS_COUNT = 5;

    private static final String DEFAULT_MEANING = "";

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
    private List<String> meanings;

    public CardMeaning(Long id, String first, String second, String third, String fourth, String fifth) {
        this.id = id;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;

        this.meanings = Stream.of(first, second, third, fourth, fifth).collect(Collectors.toList());
    }

    private CardMeaning() {
        this.first = DEFAULT_MEANING;
        this.second = DEFAULT_MEANING;
        this.third = DEFAULT_MEANING;
        this.fourth = DEFAULT_MEANING;
        this.fifth = DEFAULT_MEANING;

        List<String> meanings = Stream.of(
                getFirst(), getSecond(), getThird(), getFourth(), getFifth()
        ).collect(Collectors.toList());

        this.setMeanings(meanings);
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

    public List<String> getMeanings() {
        return meanings;
    }

    private void setMeanings(List<String> meanings) {
        if (meanings == null) {
            throw new NullPointerException("The meanings list has no reference to an object");
        }
        if (meanings.size() > 5) {
            throw new IllegalArgumentException("The meanings size is higher than " + MEANINGS_COUNT);
        }

        this.first = meanings.get(0);
        this.second = meanings.get(1);
        this.third = meanings.get(2);
        this.fourth = meanings.get(3);
        this.fifth = meanings.get(4);

        this.meanings = meanings;
    }

    public static class Builder {

        private CardMeaning meaning;

        public Builder() {
            meaning = new CardMeaning();
        }

        public Builder setId(Long id) {
            this.meaning.setId(id);
            return this;
        }

        public Builder setFirst(String meaning) {
            this.meaning.setFirst(meaning);
            return this;
        }

        public Builder setSecond(String meaning) {
            this.meaning.setSecond(meaning);
            return this;
        }

        public Builder setThird(String meaning) {
            this.meaning.setThird(meaning);
            return this;
        }

        public Builder setFourth(String meaning) {
            this.meaning.setFourth(meaning);
            return this;
        }

        public Builder setFifth(String meaning) {
            this.meaning.setFifth(meaning);
            return this;
        }

        public Builder setMeanings(List<String> meanings) {
            this.meaning.setMeanings(meanings);
            return this;
        }

        public CardMeaning getMeaning() {
            return meaning;
        }
    }
}
