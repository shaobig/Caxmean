package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.CreateDao;
import shaobig.caxmean.database.dao.abstractions.DeleteDao;
import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.entities.CardSemantic;

@Dao
public interface CardSemanticDao extends CreateDao<CardSemantic, Long>, ReadDao<CardSemantic, Long>, DeleteDao<Long> {

    @Override
    @Insert
    Long create(CardSemantic semantic);

    @Override
    @Query("SELECT * FROM card_semantic WHERE id = :id")
    CardSemantic readById(Long id);

    @Query("SELECT * FROM card_semantic")
    List<CardSemantic> readAll();

    @Override
    @Query("DELETE FROM card_semantic WHERE id = :id")
    void delete(Long id);
}