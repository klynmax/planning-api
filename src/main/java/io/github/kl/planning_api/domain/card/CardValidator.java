package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.common.validation.InvalidFields;
import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardValidator {

    @Autowired
    private CardRepository repository;

    public ValidationResult validate(CardForm form) {
        var result = ValidationResult.novo();

        if(repository.findByName(form.name()).isPresent()) {
            result.add(new InvalidFields("nome", "Já cadastrado."));
        }

        return result;
    }
}
