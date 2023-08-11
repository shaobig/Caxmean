package shaobig.caxmean.quiz;

import android.util.Log;

import java.util.List;
import java.util.Random;

import shaobig.caxmean.adapter.listeners.word.TranslationSwitchWordPackagerFactory;
import shaobig.caxmean.adapter.listeners.word.WordPackagerFactory;
import shaobig.caxmean.database.entities.meta.dto.FullCard;
import shaobig.caxmean.quiz.packager.hint.HintPackager;
import shaobig.caxmean.quiz.packager.hint.QuizHintPackagerFactory;
import shaobig.caxmean.quiz.packager.variant.QuizVariantPackagerFactory;
import shaobig.caxmean.quiz.packager.variant.VariantPackager;
import shaobig.caxmean.quiz.score.QuizScoreCounterFactory;
import shaobig.caxmean.quiz.score.Score;
import shaobig.caxmean.quiz.score.ScoreCounterFactory;

public class QuizController implements AnswerChecker, QuizIterator, AssumptionHelper, SemanticGenerator {

    private static final String NO_HINT_TEXT = "отсутствует";

    private List<FullCard> cards;

    private int iterator;
    private int index;

    private WordPackagerFactory wordPackagerFactory;

    private VariantPackager variantPackager;
    private HintPackager hintPackager;

    private QuizAnswerChecker answerChecker;

    private ScoreCounterFactory scoreCounterFactory;

    private QuizStatistics quizStatistics;

    private QuizController(List<FullCard> cards, boolean isActive) {
        this.cards = cards;

        this.wordPackagerFactory = new TranslationSwitchWordPackagerFactory(isActive);

        this.variantPackager = new QuizVariantPackagerFactory(isActive).getVariantPackager();
        this.hintPackager = new QuizHintPackagerFactory(isActive).getHintPackager();

        this.answerChecker = new QuizAnswerChecker();
        this.scoreCounterFactory = new QuizScoreCounterFactory();

        this.quizStatistics = new QuizStatistics(cards.size(), Score.ABSOLUTELY_RIGHT.getPoint());
    }

    public static QuizController getController(List<FullCard> cards, Boolean isActive) {
        return new QuizController(cards, isActive);
    }

    @Override
    public boolean check(String assumption) {
        String answer = getTranslation();

        Log.d("assumption", assumption);
        Log.d("answer", answer);

        List<String> variants = getVariantPackager().packageVariants(getCards().get(getIndex()));
        getAnswerChecker().setVariants(variants);

        boolean isRight = getAnswerChecker().check(assumption);
        setScore(assumption, variants);

        if (isRight) {
            getQuizStatistics().incrementRightAnswerCount();
        }

        getCards().remove(getIndex());

        return isRight;
    }

    @Override
    public String getNextWord() {
        setIterator(getIterator() + 1);
        setIndex(new Random().nextInt(getCards().size()));
        setHelped(false);

        return getMeaning();
    }

    @Override
    public String getMeaning() {
        return getWordPackagerFactory()
                .getNextWordPackager()
                .getNextWord(getCards().get(getIndex()));
    }

    @Override
    public String getTranslation() {
        getWordPackagerFactory().setActive(!getWordPackagerFactory().isActive());
        String translation = getMeaning();
        getWordPackagerFactory().setActive(!getWordPackagerFactory().isActive());

        return translation;
    }

    @Override
    public String getHint() {
        setHelped(true);
        getQuizStatistics().incrementHintCount();
        String hint = getHintPackager().getHint(getCards().get(getIndex()));

        return !hint.isEmpty() ? hint : NO_HINT_TEXT;
    }

    public int getIterator() {
        return iterator;
    }

    private void setIterator(int iterator) {
        this.iterator = iterator;
    }

    public List<FullCard> getCards() {
        return cards;
    }

    public int getIndex() {
        return index;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    private void setScore(String assumption, List<String> variants) {
        setScore(getScoreCounterFactory().getScoreCounter().getScore(assumption, variants));
    }

    private void setScore(int score) {
        getQuizStatistics().setScore(getQuizStatistics().getScore() + score);
    }

    private void setHelped(boolean helped) {
        getScoreCounterFactory().setHelped(helped);
    }

    public QuizStatistics getQuizStatistics() {
        return quizStatistics;
    }

    public WordPackagerFactory getWordPackagerFactory() {
        return wordPackagerFactory;
    }

    public VariantPackager getVariantPackager() {
        return variantPackager;
    }

    public HintPackager getHintPackager() {
        return hintPackager;
    }

    public ScoreCounterFactory getScoreCounterFactory() {
        return scoreCounterFactory;
    }

    public QuizAnswerChecker getAnswerChecker() {
        return answerChecker;
    }
}