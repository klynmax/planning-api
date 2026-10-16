package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ReleasesRepository
        extends JpaRepository<ReleasesEntity, UUID>,
        JpaSpecificationExecutor<ReleasesEntity> {
}
