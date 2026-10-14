package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import org.springframework.stereotype.Component;

@Component
public class ReleasesValidation {
    public ValidationResult validation (
            ReleasesForm form,
            CategoryEntity category,
            CardEntity card
    ) {
        return ValidationResult.novo();
    }
}
