package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.EntityDao;
import shaobig.caxmean.database.entities.CardMeaning;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CardMeaningDao extends EntityDao<CardMeaning, Long> {

    @Override
    @Insert(onConflict = REPLACE)
    Long create(CardMeaning meaning);

    @Update
    void update(CardMeaning meaning);

    @Override
    @Query("SELECT * FROM card_meaning WHERE id = :id")
    CardMeaning readById(Long id);

    @Query("SELECT * FROM card_meaning")
    List<CardMeaning> readAll();

    @Override
    @Query("DELETE FROM card_meaning WHERE id = :id")
    void delete(Long id);
}
