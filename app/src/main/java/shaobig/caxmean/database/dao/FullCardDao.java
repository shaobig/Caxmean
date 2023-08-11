package shaobig.caxmean.database.dao;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Query;

import java.util.List;

import shaobig.caxmean.database.dao.abstractions.ReadDao;
import shaobig.caxmean.database.dao.abstractions.UpdateDao;
import shaobig.caxmean.database.entities.meta.dto.FullCard;

@Dao
public interface FullCardDao extends ReadDao<FullCard, Long>, UpdateDao<FullCard> {

    @Override
    @Query("SELECT " +
            "card.id AS card_id," +
            " card.collection_id AS collection_id, " +
            "   card_meaning.id AS meaning_id, " +
            "   card_meaning.first AS first_meaning, " +
            "   card_meaning.second AS second_meaning, " +
            "   card_meaning.third AS third_meaning, " +
            "   card_meaning.fourth AS fourth_meaning, " +
            "   card_meaning.fifth AS fifth_meaning, " +
            " card_translation.id AS translation_id, " +
            "   card_translation.first AS first_translation, " +
            "   card_translation.second AS second_translation, " +
            "   card_translation.third AS third_translation, " +
            "   card_translation.fourth AS fourth_translation, " +
            "   card_translation.fifth AS fifth_translation," +
            " card.semantic_id AS semantic_id, " +
            " card.hint_id AS hint_id, " +
            "   card_hint.native_hint AS native_hint, " +
            "   card_hint.foreign_hint AS foreign_hint, " +
            " card.transcription AS transcription " +
            "FROM card " +
            "INNER JOIN card_semantic ON card.semantic_id = card_semantic.id " +
            "INNER JOIN card_meaning ON card_semantic.meaning_id = card_meaning.id " +
            "INNER JOIN card_translation ON card_semantic.translation_id = card_translation.id " +
            "INNER JOIN card_hint ON card.hint_id = card_hint.id " +
            "WHERE card_id = :id")
    FullCard readById(Long id);

    @Override
    @Query("SELECT " +
            "card.id AS card_id," +
            " card.collection_id AS collection_id, " +
            " card_meaning.id AS meaning_id, " +
            "   card_meaning.first AS first_meaning, " +
            "   card_meaning.second AS second_meaning, " +
            "   card_meaning.third AS third_meaning, " +
            "   card_meaning.fourth AS fourth_meaning, " +
            " card_meaning.fifth AS fifth_meaning, " +
            " card_translation.id AS translation_id, " +
            "   card_translation.first AS first_translation, " +
            "   card_translation.second AS second_translation, " +
            "   card_translation.third AS third_translation, " +
            "   card_translation.fourth AS fourth_translation, " +
            "   card_translation.fifth AS fifth_translation," +
            " card.semantic_id AS semantic_id, " +
            " card.hint_id AS hint_id, " +
            "   card_hint.native_hint AS native_hint, " +
            "   card_hint.foreign_hint AS foreign_hint, " +
            " card.transcription AS transcription " +
            "FROM card " +
            "INNER JOIN card_semantic ON card.semantic_id = card_semantic.id " +
            "INNER JOIN card_meaning ON card_semantic.meaning_id = card_meaning.id " +
            "INNER JOIN card_translation ON card_semantic.translation_id = card_translation.id " +
            "INNER JOIN card_hint ON card.hint_id = card_hint.id")
    List<FullCard> readAll();

    @Query("SELECT " +
            "card.id AS card_id," +
            " card.collection_id AS collection_id, " +
            "   card_meaning.id AS meaning_id, " +
            "   card_meaning.first AS first_meaning, " +
            "   card_meaning.second AS second_meaning, " +
            "   card_meaning.third AS third_meaning, " +
            "   card_meaning.fourth AS fourth_meaning, " +
            "   card_meaning.fifth AS fifth_meaning, " +
            " card_translation.id AS translation_id, " +
            "   card_translation.first AS first_translation, " +
            "   card_translation.second AS second_translation, " +
            "   card_translation.third AS third_translation, " +
            "   card_translation.fourth AS fourth_translation, " +
            "   card_translation.fifth AS fifth_translation," +
            " card_semantic.id AS semantic_id, " +
            " card.hint_id AS hint_id, " +
            "   card_hint.native_hint AS native_hint, " +
            "   card_hint.foreign_hint AS foreign_hint, " +
            " card.transcription AS transcription " +
            "FROM card " +
            "INNER JOIN card_semantic ON card.semantic_id = card_semantic.id " +
            "INNER JOIN card_meaning ON card_semantic.meaning_id = card_meaning.id " +
            "INNER JOIN card_translation ON card_semantic.translation_id = card_translation.id " +
            "INNER JOIN card_hint ON card.hint_id = card_hint.id " +
            "WHERE collection_id = :id")
    List<FullCard> readAllCollectionCards(Long id);

    @Query("UPDATE card_meaning SET first = :first, second = :second, third = :third, fourth = :fourth, fifth = :fifth WHERE id = :id")
    void updateMeanings(Long id, String first, String second, String third, String fourth, String fifth);

    @Query("UPDATE card_translation SET first = :first, second = :second, third = :third, fourth = :fourth, fifth = :fifth WHERE id = :id")
    void updateTranslations(Long id, String first, String second, String third, String fourth, String fifth);

    @Query("UPDATE card_hint SET native_hint = :nativeHint, foreign_hint = :foreignHint WHERE id = :id")
    void updateHints(Long id, String nativeHint, String foreignHint);

    @Query("UPDATE card SET transcription = :transcription WHERE id = :id")
    void updateTranscription(Long id, String transcription);

    @Override
    @Ignore
    default void update(FullCard card) {
        updateMeanings(
                card.getMeaningId(),
                card.getFirstMeaning(),
                card.getSecondMeaning(),
                card.getThirdMeaning(),
                card.getFourthMeaning(),
                card.getFifthMeaning()
        );
        updateTranslations(
                card.getTranslationId(),
                card.getFirstTranslation(),
                card.getSecondTranslation(),
                card.getThirdTranslation(),
                card.getFourthTranslation(),
                card.getFifthTranslation()
        );
        updateHints(card.getHintId(), card.getNativeHint(), card.getForeignHint());
        updateTranscription(card.getCardId(), card.getTranscription());
    }
}
