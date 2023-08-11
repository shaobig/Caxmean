package shaobig.caxmean.quiz.score;

import static shaobig.caxmean.quiz.score.Score.Point.ABSOLUTELY_RIGHT_SCORE;
import static shaobig.caxmean.quiz.score.Score.Point.ABSOLUTELY_RIGHT_WITH_HINT_SCORE;
import static shaobig.caxmean.quiz.score.Score.Point.RIGHT_WITH_HINT_SCORE;
import static shaobig.caxmean.quiz.score.Score.Point.WRONG_SCORE;
import static shaobig.caxmean.quiz.score.Score.Point.WRONG_WITH_HINT_SCORE;

public enum Score {
    ABSOLUTELY_RIGHT(ABSOLUTELY_RIGHT_SCORE),
    ABSOLUTELY_RIGHT_WITH_HINT(ABSOLUTELY_RIGHT_WITH_HINT_SCORE),
    RIGHT(Point.RIGHT_SCORE),
    RIGHT_WITH_HINT(RIGHT_WITH_HINT_SCORE),
    WRONG(WRONG_SCORE),
    WRONG_WITH_HINT(WRONG_WITH_HINT_SCORE),
    ;

    private int point;

    Score(int point) {
        this.point = point;
    }

    protected static final class Point {
        protected static final Integer ABSOLUTELY_RIGHT_SCORE = 5;
        protected static final Integer ABSOLUTELY_RIGHT_WITH_HINT_SCORE = 3;
        protected static final Integer RIGHT_SCORE = 2;
        protected static final Integer RIGHT_WITH_HINT_SCORE = 1;
        protected static final Integer WRONG_SCORE = 0;
        protected static final Integer WRONG_WITH_HINT_SCORE = -3;
    }

    public int getPoint() {
        return point;
    }
}
