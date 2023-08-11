package shaobig.caxmean.quiz.score;

import java.util.List;

public class IndependentScoreCounter implements ScoreCounter {
    @Override
    public int getScore(String assumption, List<String> answers) {
        boolean isAbsolutelyRight = answers.stream()
                .anyMatch(answer -> answer.toLowerCase().equals(assumption.trim().toLowerCase()));

        if (isAbsolutelyRight) {
            return Score.ABSOLUTELY_RIGHT.getPoint();
        }

        boolean isRight = answers.stream()
                .anyMatch(answer -> answer.toLowerCase().contains(assumption.trim().toLowerCase()));

        return isRight
                ? Score.RIGHT.getPoint()
                : Score.WRONG.getPoint();
    }
}