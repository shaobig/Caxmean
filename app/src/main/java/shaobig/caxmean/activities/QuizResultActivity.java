package shaobig.caxmean.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import shaobig.caxmean.R;
import shaobig.caxmean.quiz.QuizStatistics;

import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_ID;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_NAME;
import static shaobig.caxmean.meta.ActivityKeyKeeper.QUIZ_STATISTICS_KEY;

public class QuizResultActivity extends AppCompatActivity {

    private static final String POSITIVE_VERDICT = "Экзамен сдан";
    private static final String NEGATIVE_VERDICT = "Экзамен не сдан";

    private TextView verdictView;
    private TextView questionsView;
    private TextView scoreView;
    private TextView hintView;

    private Long collectionId;
    private String collectionName;

    private QuizStatistics quizStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        verdictView = findViewById(R.id.quiz_result_verdict);
        questionsView = findViewById(R.id.quiz_result_questions);
        scoreView = findViewById(R.id.quiz_result_score);
        hintView = findViewById(R.id.quiz_result_hint);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            setCollectionId(getIntent().getExtras().getLong(CARD_COLLECTION_ID));
            setCollectionName(getIntent().getExtras().getString(CARD_COLLECTION_NAME));

            QuizStatistics quizStatistics = (QuizStatistics) getIntent().getExtras().get(QUIZ_STATISTICS_KEY);
            setQuizStatistics(quizStatistics);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getQuestionsView().setText(
                String.valueOf(getQuizStatistics().getRightAnswerCount())
                        .concat("/")
                        .concat(String.valueOf(getQuizStatistics().getQuestionCount()))
        );
        getScoreView().setText(
                String.valueOf(getQuizStatistics().getScore())
                        .concat("/")
                        .concat(String.valueOf(getQuizStatistics().getMaxScore()))
                        .concat("\n")
                        .concat("(min. ")
                        .concat(String.valueOf(getQuizStatistics().getMinScore()))
                        .concat(")")
        );
        getHintView().setText(
                String.valueOf(getQuizStatistics().getHintCount())
                        .concat("/")
                        .concat(String.valueOf(getQuizStatistics().getQuestionCount()))
        );

        if (getQuizStatistics().isPassed()) {
            getVerdictView().setText(POSITIVE_VERDICT);
            getVerdictView().setTextColor(Color.GREEN);
        } else {
            getVerdictView().setText(NEGATIVE_VERDICT);
            getVerdictView().setTextColor(Color.RED);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, CardCollectionActivity.class);
        intent.putExtra(CARD_COLLECTION_ID, getCollectionId());
        intent.putExtra(CARD_COLLECTION_NAME, getCollectionName());

        startActivity(intent);
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public void setCollectionName(String collectionName) {
        setTitle(collectionName);
        this.collectionName = collectionName;
    }

    public QuizStatistics getQuizStatistics() {
        return quizStatistics;
    }

    public void setQuizStatistics(QuizStatistics quizStatistics) {
        this.quizStatistics = quizStatistics;
    }

    public TextView getVerdictView() {
        return verdictView;
    }

    public TextView getQuestionsView() {
        return questionsView;
    }

    public TextView getScoreView() {
        return scoreView;
    }

    public TextView getHintView() {
        return hintView;
    }
}