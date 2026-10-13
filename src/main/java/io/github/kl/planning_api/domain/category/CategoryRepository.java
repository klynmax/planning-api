package io.github.kl.planning_api.domain.category;

import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByName(String name);
}
