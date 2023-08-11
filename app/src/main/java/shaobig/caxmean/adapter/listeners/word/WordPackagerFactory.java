package shaobig.caxmean.adapter.listeners.word;

public abstract class WordPackagerFactory {

    private boolean isActive;

    public WordPackagerFactory(boolean isActive) {
        this.isActive = isActive;
    }

    public abstract WordPackager getNextWordPackager();

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
