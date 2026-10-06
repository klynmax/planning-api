package io.github.kl.planning_api.domain.card.dto;

import io.github.kl.planning_api.domain.card.model.CardBrand;

public record CardForm(String name, CardBrand brand) {
}
