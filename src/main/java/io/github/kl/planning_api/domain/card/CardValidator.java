package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.common.validation.InvalidFields;
import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CardValidator {

    @Autowired
    private CardRepository repository;

    public ValidationResult validate(CardForm form, UUID id) {
        var result = ValidationResult.novo();

        var isNotEmptyList = !repository.findByNameAndNotId(form.name(), id).isEmpty();

        if(isNotEmptyList) {
            result.add(new InvalidFields("name", "Já cadastrado."));
        }

        return result;
    }
}
