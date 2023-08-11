package shaobig.caxmean.quiz.score;

import java.util.List;

public interface ScoreCounter {
    int getScore(String assumption, List<String> answers);
}
