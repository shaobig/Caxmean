package shaobig.caxmean.quiz;

import java.io.Serializable;
import java.math.BigDecimal;

public class QuizStatistics implements Serializable, ScoreExaminator {

    private static final String MIN_PASS_COEFFICIENT = "0.6";

    private int questionCount;

    private int rightAnswerCount;
    private int hintCount;

    private int score;
    private int rightAnswerValue;

    public QuizStatistics(int questionCount, int rightAnswerValue) {
        this.questionCount = questionCount;
        this.rightAnswerValue = rightAnswerValue;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public int getRightAnswerCount() {
        return rightAnswerCount;
    }

    private void setRightAnswerCount(int rightAnswerCount) {
        this.rightAnswerCount = rightAnswerCount;
    }

    public int getWrongAnswerCount() {
        return getQuestionCount() - getRightAnswerCount();
    }

    public int getHintCount() {
        return hintCount;
    }

    private void setHintCount(int hintCount) {
        this.hintCount = hintCount;
    }

    public void incrementRightAnswerCount() {
        setRightAnswerCount(getRightAnswerCount() + 1);
    }

    public void incrementHintCount() {
        setHintCount(getHintCount() + 1);
    }

    public int getRightAnswerValue() {
        return rightAnswerValue;
    }

    @Override
    public BigDecimal getPassCoefficient() {
        return new BigDecimal(MIN_PASS_COEFFICIENT);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMinScore() {
        return getPassCoefficient().multiply(new BigDecimal(String.valueOf(getMaxScore()))).intValue();
    }

    public int getMaxScore() {
        return getQuestionCount() * getRightAnswerValue();
    }

    public boolean isPassed() {
        return getScore() >= getMinScore();
    }
}
