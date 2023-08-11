package shaobig.caxmean.quiz.packager.variant;

import java.util.List;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class MeaningVariantPackager implements VariantPackager {
    @Override
    public List<String> packageVariants(FullCard card) {
        return card.getMeanings();
    }
}
