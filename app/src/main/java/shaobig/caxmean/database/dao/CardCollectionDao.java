package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.CreateDao;
import shaobig.caxmean.database.dao.abstractions.DeleteDao;
import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.dao.abstractions.UpdateDao;
import shaobig.caxmean.database.entities.CardCollection;

@Dao
public interface CardCollectionDao extends CreateDao<CardCollection, Long>, ReadDao<CardCollection, Long>, UpdateDao<CardCollection>, DeleteDao<Long> {

    @Override
    @Insert
    Long create(CardCollection collection);

    @Override
    @Query("SELECT * FROM card_collection WHERE id = :id")
    CardCollection readById(Long id);

    @Query("SELECT * FROM card_collection")
    List<CardCollection> readAll();

    @Override
    @Update
    void update(CardCollection collection);

    @Query("UPDATE card_collection SET edited = :edited WHERE id = :id")
    void updateTime(Long id, String edited);

    @Override
    @Query("DELETE FROM card_collection WHERE id = :id")
    void delete(Long id);
}
