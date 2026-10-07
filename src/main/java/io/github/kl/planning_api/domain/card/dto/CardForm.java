package io.github.kl.planning_api.domain.card.dto;

import io.github.kl.planning_api.domain.card.model.CardBrand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardForm(
        @NotBlank(message = "Campo obrigatório.")
        String name,
        @NotNull(message = "Campo obrigatório.")
        CardBrand brand) {
}
