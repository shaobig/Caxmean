package shaobig.caxmean.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizAnswerChecker implements AnswerChecker {

    private List<String> variants;

    public QuizAnswerChecker() {
        this.variants = new ArrayList<>();
    }

    public QuizAnswerChecker(List<String> variants) {
        this.variants = variants;
    }

    @Override
    public boolean check(String assumption) {
        return getVariants().stream()
                .anyMatch(p -> p.toLowerCase().contains(assumption.trim().toLowerCase()));
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }
}
