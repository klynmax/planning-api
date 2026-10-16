package io.github.kl.planning_api.domain.releases.dto;

import io.github.kl.planning_api.domain.releases.model.ReleaseType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReleasesDetail(
        String id,
        LocalDate date,
        String description,
        BigDecimal value,
        ReleaseType type,
        String categoryId,
        String categoryName,
        String cardId,
        String cardName
) {}
