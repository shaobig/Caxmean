package shaobig.caxmean.quiz.score;

import java.util.List;

public class HintScoreCounter implements ScoreCounter {
    @Override
    public int getScore(String assumption, List<String> answers) {
        boolean isAbsolutelyRight = answers.stream()
                .anyMatch(m -> m.equals(assumption));

        if (isAbsolutelyRight) {
            return Score.ABSOLUTELY_RIGHT_WITH_HINT.getPoint();
        }

        boolean isRight = answers.stream()
                .anyMatch(m -> m.contains(assumption));

        return isRight
                ? Score.RIGHT_WITH_HINT.getPoint()
                : Score.WRONG_WITH_HINT.getPoint();
    }
}
