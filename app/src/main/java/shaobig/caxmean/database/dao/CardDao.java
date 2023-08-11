package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.CreateDao;
import shaobig.caxmean.database.dao.abstractions.DeleteDao;
import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.entities.Card;
import shaobig.caxmean.database.entities.meta.associations.CardAndHint;

@Dao
public interface CardDao extends CreateDao<Card, Long>, ReadDao<Card, Long>, DeleteDao<Long> {

    //CREATE

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long create(Card card);

    //READ

    @Override
    @Query("SELECT * FROM card WHERE collection_id = :id")
    Card readById(Long id);

    @Override
    @Query("SELECT * FROM card")
    List<Card> readAll();

    @Query("SELECT * FROM card WHERE hint_id = :id")
    @Transaction
    List<CardAndHint> readAllCardHints(Long id);

    @Query("SELECT *  FROM card WHERE collection_id = :id")
    List<Card> readAllCollectionCards(Long id);

    //DELETE

    @Query("DELETE FROM card WHERE collection_id = :collectionId")
    void deleteAllCards(Long collectionId);

    @Override
    @Query("DELETE FROM card WHERE id = :id")
    void delete(Long id);
}
