package io.github.kl.planning_api.domain.dashboard.dto;

import java.math.BigDecimal;

public record ExpenseCategorySummary(
        String category,
        BigDecimal value
) {
}
