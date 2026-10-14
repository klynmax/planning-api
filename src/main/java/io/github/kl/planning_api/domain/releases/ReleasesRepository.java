package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReleasesRepository extends JpaRepository<ReleasesEntity, UUID> {
}
