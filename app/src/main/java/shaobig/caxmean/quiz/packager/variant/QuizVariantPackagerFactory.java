package shaobig.caxmean.quiz.packager.variant;

public class QuizVariantPackagerFactory implements VariantPackagerFactory {

    private boolean isActive;

    public QuizVariantPackagerFactory(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public VariantPackager getVariantPackager() {
        return isActive()
                ? new MeaningVariantPackager()
                : new TranslationVariantPackager();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}