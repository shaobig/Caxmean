package shaobig.caxmean.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shaobig.caxmean.R;
import shaobig.caxmean.database.CardDatabase;
import shaobig.caxmean.quiz.QuizController;

import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_ID;
import static shaobig.caxmean.meta.ActivityKeyKeeper.CARD_COLLECTION_NAME;
import static shaobig.caxmean.meta.ActivityKeyKeeper.EXECUTOR_THREADS_COUNT;
import static shaobig.caxmean.meta.ActivityKeyKeeper.QUIZ_STATISTICS_KEY;
import static shaobig.caxmean.meta.ActivityKeyKeeper.TRANSLATION_SWITCH_KEY;

public class QuizActivity extends AppCompatActivity {

    private static final String RIGHT_ANSWER_TEXT = "Правильный ответ: ";

    private static final Integer DEFAULT_DELAY = 2000;

    private TextView wordView;
    private TextView hintView;
    private TextView counterView;
    private TextView scoreView;
    private TextView rightAnswerView;

    private EditText assumptionEditText;

    private Button hintButton;
    private Button acceptButton;

    private CardDatabase database;
    private ExecutorService executor;

    private Long collectionId;
    private String collectionName;

    private QuizController quizController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        wordView = findViewById(R.id.quiz_word);
        hintView = findViewById(R.id.quiz_hint);
        counterView = findViewById(R.id.quiz_counter);
        scoreView = findViewById(R.id.quiz_score);
        rightAnswerView = findViewById(R.id.quiz_right_answer);

        assumptionEditText = findViewById(R.id.quiz_assumption);

        hintButton = findViewById(R.id.quiz_hint_button);
        acceptButton = findViewById(R.id.quiz_accept_button);

        database = CardDatabase.getDatabase(this);
        executor = Executors.newFixedThreadPool(EXECUTOR_THREADS_COUNT);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        hintButton.setOnClickListener(v -> {
            getHintButton().setEnabled(false);
            getHintView().setText("Подсказка: ".concat(getQuizController().getHint()));
        });
        acceptButton.setOnClickListener(v -> {
            if (getAssumptionEditText().getText().toString().isEmpty()) {
                Toast.makeText(this, "Пожалуйста, внесите своё предположение в форму", Toast.LENGTH_SHORT).show();
            } else {
                getHintButton().setEnabled(false);
                getAcceptButton().setEnabled(false);

                checkAnswer();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            getExecutor().execute(() -> {
                collectionId = bundle.getLong(CARD_COLLECTION_ID);
                collectionName = bundle.getString(CARD_COLLECTION_NAME);

                quizController = QuizController.getController(getDatabase().getFullCardDao().readAllCollectionCards(getCollectionId()), bundle.getBoolean(TRANSLATION_SWITCH_KEY));

                runOnUiThread(this::prepareTextFields);
            });
        }
    }

    public void checkAnswer() {
        String assumption = getAssumptionEditText().getText().toString();

        getRightAnswerView().setText(RIGHT_ANSWER_TEXT.concat(getQuizController().getTranslation()));

        if (getQuizController().check(assumption)) {
            getWordView().setTextColor(Color.GREEN);
        } else {
            getWordView().setTextColor(Color.RED);
        }

        new Handler().postDelayed(() -> {
            if (getQuizController().getCards().isEmpty()) {
                Intent intent = new Intent(this, QuizResultActivity.class);

                intent.putExtra(CARD_COLLECTION_ID, getCollectionId());
                intent.putExtra(CARD_COLLECTION_NAME, getCollectionName());
                intent.putExtra(QUIZ_STATISTICS_KEY, getQuizController().getQuizStatistics());

                startActivity(intent);
            } else {
                prepareTextFields();
            }
        }, DEFAULT_DELAY);
    }

    public void prepareTextFields() {
        getHintButton().setEnabled(true);
        getAcceptButton().setEnabled(true);

        getWordView().setText(getQuizController().getNextWord());
        getWordView().setTextColor(getScoreView().getTextColors().getDefaultColor());

        getScoreView().setText("Счёт: ".concat(String.valueOf(getQuizController().getQuizStatistics().getScore())));
        getCounterView().setText(
                String.valueOf(getQuizController().getIterator())
                        .concat("/")
                        .concat(String.valueOf((getQuizController().getCards().size() - 1) + getQuizController().getIterator()))
        );

        getAssumptionEditText().setText("");
        getHintView().setText("");

        getRightAnswerView().setText("");
    }

    public CardDatabase getDatabase() {
        return database;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public TextView getWordView() {
        return wordView;
    }

    public TextView getHintView() {
        return hintView;
    }

    public TextView getScoreView() {
        return scoreView;
    }

    public TextView getCounterView() {
        return counterView;
    }

    public EditText getAssumptionEditText() {
        return assumptionEditText;
    }

    public QuizController getQuizController() {
        return quizController;
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

    public Button getHintButton() {
        return hintButton;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public TextView getRightAnswerView() {
        return rightAnswerView;
    }
}