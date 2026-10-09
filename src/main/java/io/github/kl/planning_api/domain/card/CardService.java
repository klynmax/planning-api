package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.common.exceptions.RecordNotFoundException;
import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import io.github.kl.planning_api.domain.card.mapper.CardMapper;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardValidator validator;
    @Autowired
    private CardRepository repository;
    @Autowired
    private CardMapper mapper;

    public CardDetails create(CardForm form){
        var result = validator.validate(form, null);

        if(result.isInvalid()) {
            throw new ValidationException(result.getInvalidFields());
        }

        CardEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetails(entity);
    }

    public CardDetails getDetails(UUID id) {
        return repository.findById(id)
                .map(mapper::toDetails)
                .orElseThrow(() -> new RecordNotFoundException());
    }

    @Transactional
    public void update(UUID id, CardForm updateData) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException());

        var result = validator.validate(updateData, id);

        if(result.isInvalid()) {
            throw  new ValidationException(result.getInvalidFields());
        }

        mapper.update(entity, updateData);
    }

    public Page<CardDetails> getList(PageRequest pageRequest) {
        return repository
                .findAll(pageRequest)
                .map(mapper::toDetails);
    }

    @Transactional
    public void updateStatus(UUID id) {
        var card = repository.findById(id).orElseThrow(RecordNotFoundException::new);

        card.setActive(!card.getActive());
    }
}
