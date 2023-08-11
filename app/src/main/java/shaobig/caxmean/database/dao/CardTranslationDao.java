package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.CreateDao;
import shaobig.caxmean.database.dao.abstractions.DeleteDao;
import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.entities.CardTranslation;

@Dao
public interface CardTranslationDao extends CreateDao<CardTranslation, Long>, ReadDao<CardTranslation, Long>, DeleteDao<Long> {

    @Override
    @Insert
    Long create(CardTranslation translation);

    @Override
    @Query("SELECT * FROM card_translation WHERE id = :id")
    CardTranslation readById(Long id);

    @Query("SELECT * FROM card_translation")
    List<CardTranslation> readAll();

    @Override
    @Query("DELETE FROM card_translation WHERE id = :id")
    void delete(Long id);
}
