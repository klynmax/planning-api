package io.github.kl.planning_api.domain.card.dto;

import io.github.kl.planning_api.domain.card.model.CardBrand;

import java.time.LocalDateTime;

public record CardDetails(String id, String name, CardBrand brand, LocalDateTime registrationDate) {
}
