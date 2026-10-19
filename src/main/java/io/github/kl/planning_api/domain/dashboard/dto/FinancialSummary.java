package io.github.kl.planning_api.domain.dashboard.dto;

import java.math.BigDecimal;

public record FinancialSummary(
        BigDecimal revenues,
        BigDecimal expenses,
        BigDecimal balance
) {
}
