package shaobig.caxmean.quiz.packager.variant;

import java.util.List;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public interface VariantPackager {
    List<String> packageVariants(FullCard card);
}
