package io.github.kl.planning_api.domain.category.mapper;

import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.category.dto.CategoryDetails;
import io.github.kl.planning_api.domain.category.dto.CategoryForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity toEntity(CategoryForm newCategory);

    CategoryDetails toDetails(CategoryEntity entity);
}
