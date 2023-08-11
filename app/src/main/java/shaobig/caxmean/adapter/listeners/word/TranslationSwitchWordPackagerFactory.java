package shaobig.caxmean.adapter.listeners.word;

public class TranslationSwitchWordPackagerFactory extends WordPackagerFactory {

    public TranslationSwitchWordPackagerFactory(boolean isActive) {
        super(isActive);
    }

    @Override
    public WordPackager getNextWordPackager() {
        return isActive()
                ? new FirstTranslationWordPackager()
                : new FirstMeaningWordPackager();
    }
}
