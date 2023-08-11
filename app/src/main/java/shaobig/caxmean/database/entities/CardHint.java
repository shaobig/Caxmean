package shaobig.caxmean.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_hint")
public class CardHint implements IdEntity<Long> {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "native_hint")
    private String nativeHint;
    @ColumnInfo(name = "foreign_hint")
    private String foreignHint;

    public CardHint(Long id, String nativeHint, String foreignHint) {
        this.id = id;
        this.nativeHint = nativeHint;
        this.foreignHint = foreignHint;
    }

    @Ignore
    public CardHint(String nativeHint, String foreignHint) {
        this.nativeHint = nativeHint;
        this.foreignHint = foreignHint;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
}
