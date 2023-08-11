package shaobig.caxmean.quiz.packager.hint;

public class QuizHintPackagerFactory implements HintPackagerFactory {

    private boolean isActive;

    public QuizHintPackagerFactory(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public HintPackager getHintPackager() {
        return isActive()
                ? new ForeignHintPackager()
                : new NativeHintPackager();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
