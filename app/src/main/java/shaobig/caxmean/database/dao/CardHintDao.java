package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.CreateDao;
import shaobig.caxmean.database.dao.abstractions.DeleteDao;
import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.entities.CardHint;

@Dao
public interface CardHintDao extends CreateDao<CardHint, Long>, ReadDao<CardHint, Long>, DeleteDao<Long> {

    @Override
    @Insert
    Long create(CardHint hint);

    @Override
    @Query("SELECT * FROM card_hint WHERE id = :id")
    CardHint readById(Long id);

    @Override
    @Query("SELECT * FROM card_hint")
    List<CardHint> readAll();

    @Override
    @Query("DELETE FROM card_hint WHERE id = :id")
    void delete(Long id);
}
