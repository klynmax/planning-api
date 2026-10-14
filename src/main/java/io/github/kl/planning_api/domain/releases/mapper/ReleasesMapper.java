package io.github.kl.planning_api.domain.releases.mapper;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReleasesMapper {
    ReleasesEntity toEntity(ReleasesForm form, CategoryEntity category, CardEntity card);

    ReleasesDetail toDetails(ReleasesEntity entity);
}
