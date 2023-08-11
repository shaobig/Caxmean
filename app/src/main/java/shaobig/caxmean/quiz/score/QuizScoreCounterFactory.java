package shaobig.caxmean.quiz.score;

public class QuizScoreCounterFactory extends ScoreCounterFactory {

    @Override
    public ScoreCounter getScoreCounter() {
        return isHelped() ? new HintScoreCounter() : new IndependentScoreCounter();
    }
}
