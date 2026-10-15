package io.github.kl.planning_api.domain.releases.dto;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReleasesForm(
        @NotNull(message = "Campo obrigatório.")
        UUID categoryId,
        @NotNull(message = "Campo obrigatório.")
        String description,
        @NotNull(message = "Campo obrigatório.")
        ReleaseType type,
        @NotNull(message = "Campo obrigatório.")
        LocalDate date,
        @NotNull(message = "Campo obrigatório.")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero.")
        BigDecimal value,
        UUID cardId
) {}
