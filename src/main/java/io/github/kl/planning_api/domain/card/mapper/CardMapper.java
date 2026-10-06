package io.github.kl.planning_api.domain.card.mapper;

import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardEntity toEntity(CardForm form);
    CardDetails toDetails(CardEntity entity);
}
