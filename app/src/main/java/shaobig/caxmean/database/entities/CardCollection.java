package shaobig.caxmean.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

@Entity(tableName = "card_collection")
public class CardCollection implements IdEntity<Long>, Serializable {

    private static final Integer DEFAULT_PRIORITY = 1;

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "name", defaultValue = "Untitled")
    private String name;
    @ColumnInfo(name = "created")
    private String created;
    @ColumnInfo(name = "edited")
    private String edited;
    @ColumnInfo(name = "priority")
    private Integer priority;

    public CardCollection(Long id, String name, String created, String edited, Integer priority) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.edited = edited;
        this.priority = priority;
    }

    @Ignore
    public CardCollection(String name) {
        this.name = name;
        this.priority = DEFAULT_PRIORITY;

        this.created = getCurrentTime();
        this.edited = getCurrentTime();
    }

    @Ignore
    public CardCollection(String name, Integer priority) {
        this(name);
        this.priority = priority;
    }

    @Ignore
    public CardCollection(Long id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return formatter.format(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardCollection that = (CardCollection) o;
        return Objects.equals(id, that.id) &&
                name.equals(that.name) &&
                Objects.equals(created, that.created) &&
                Objects.equals(edited, that.edited) &&
                priority.equals(that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created, edited, priority);
    }
}
