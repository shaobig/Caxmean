package shaobig.caxmean.quiz.packager.variant;

import java.util.List;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class TranslationVariantPackager implements VariantPackager {
    @Override
    public List<String> packageVariants(FullCard card) {
        return card.getTranslations();
    }
}
