package io.github.kl.planning_api.domain.card.mapper;

import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(source = "brand", target = "cardBrand")
    CardEntity toEntity(CardForm form);
    @Mapping(source = "cardBrand", target = "brand")
    CardDetails toDetails(CardEntity entity);

    @Mapping(source = "brand", target = "cardBrand")
    void update(@MappingTarget CardEntity entity, CardForm updateData);
}
