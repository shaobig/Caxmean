package shaobig.caxmean.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import shaobig.caxmean.database.dao.CardCollectionDao;
import shaobig.caxmean.database.dao.CardDao;
import shaobig.caxmean.database.dao.CardHintDao;
import shaobig.caxmean.database.dao.CardMeaningDao;
import shaobig.caxmean.database.dao.CardSemanticDao;
import shaobig.caxmean.database.dao.CardTranslationDao;
import shaobig.caxmean.database.dao.FullCardDao;
import shaobig.caxmean.database.entities.Card;
import shaobig.caxmean.database.entities.CardCollection;
import shaobig.caxmean.database.entities.CardHint;
import shaobig.caxmean.database.entities.CardMeaning;
import shaobig.caxmean.database.entities.CardSemantic;
import shaobig.caxmean.database.entities.CardTranslation;

@Database(
        version = 1,
        exportSchema = false,
        entities = {
                CardCollection.class,
                CardMeaning.class,
                CardTranslation.class,
                CardHint.class,
                CardSemantic.class,
                Card.class
        }
)
public abstract class CardDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "card.db";

    private static volatile CardDatabase database;

    private static final Object lockThread = new Object();

    public static CardDatabase getDatabase(Context context) {
        if (database == null) {
            synchronized (lockThread) {
                if (database == null) {
                    database = Room.databaseBuilder(context, CardDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return database;
    }

    public static CardDatabase restoreDatabase(Context context) {
        if (database == null) {
            synchronized (lockThread) {
                if (database == null) {
                    database = Room.databaseBuilder(context, CardDatabase.class, DATABASE_NAME)
                            .createFromAsset(DATABASE_NAME)
                            .build();
                }
            }
        }

        return database;
    }

    public abstract CardCollectionDao getCardCollectionDao();
    public abstract CardMeaningDao getCardMeaningDao();
    public abstract CardTranslationDao getCardTranslationDao();
    public abstract CardHintDao getCardHintDao();
    public abstract CardSemanticDao getCardSemanticDao();
    public abstract CardDao getCardDao();
    public abstract FullCardDao getFullCardDao();
}
