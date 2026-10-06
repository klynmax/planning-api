package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {
}
