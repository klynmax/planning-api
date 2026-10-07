package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import io.github.kl.planning_api.domain.card.mapper.CardMapper;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardValidator validator;
    @Autowired
    private CardRepository repository;
    @Autowired
    private CardMapper mapper;

    public CardDetails create(CardForm form){
        var result = validator.validate(form);

        if(result.isInvalid()) {
            throw new ValidationException(result.getInvalidFields());
        }

        CardEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetails(entity);
    }
}
