package io.github.kl.planning_api.domain.category;

import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByName(String name);

    List<CategoryEntity> findByActiveTrue();
}
