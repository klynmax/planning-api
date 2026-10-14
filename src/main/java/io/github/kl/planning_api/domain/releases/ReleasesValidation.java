package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.common.validation.InvalidFields;
import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import org.springframework.stereotype.Component;

@Component
public class ReleasesValidation {
    public ValidationResult validation (
            ReleasesForm form,
            CategoryEntity category,
            CardEntity card
    ) {
        var result = ValidationResult.novo();

        if(category == null) {
            result.add(new InvalidFields("categoryId", "Categoria não encontrada."));
        } else if (Boolean.FALSE.equals(category.getActive())) {
            result.add(new InvalidFields("categoryId", "Categoria inativa."));
        }

        if(form.type() == ReleaseType.REVENUE && card != null) {
            result.add(new InvalidFields("cardId", "Lançamento do tipo RECEITA não aceita cartão."));
        }

        if(form.type() == ReleaseType.EXPENSES && form.cardId() != null) {
            if(card == null) {
                result.add(new InvalidFields("cardId", "Cartão não encontrado."));
            } else if (Boolean.FALSE.equals(card.getActive())) {
                result.add((new InvalidFields("cardId", "Cartão invalido")));
            }
        }

        return result;
    }
}
