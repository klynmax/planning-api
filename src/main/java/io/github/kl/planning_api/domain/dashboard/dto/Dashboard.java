package io.github.kl.planning_api.domain.dashboard.dto;

import java.util.List;

public record Dashboard(
        String month,
        FinancialSummary summary,
        List<ExpenseCategorySummary> expenseByCategory
) {
}
