package io.github.kl.planning_api.domain.releases.mapper;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReleasesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "card", source = "card")
    ReleasesEntity toEntity(ReleasesForm form, CategoryEntity category, CardEntity card);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "cardId", source = "card.id")
    @Mapping(target = "cardName", source = "card.name")
    ReleasesDetail toDetails(ReleasesEntity entity);
}
