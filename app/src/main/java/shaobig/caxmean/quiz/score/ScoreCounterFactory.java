package shaobig.caxmean.quiz.score;

public abstract class ScoreCounterFactory {
    private boolean isHelped;

    public abstract ScoreCounter getScoreCounter();

    public boolean isHelped() {
        return isHelped;
    }

    public void setHelped(boolean helped) {
        isHelped = helped;
    }
}
