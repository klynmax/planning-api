package io.github.kl.planning_api.domain.dashboard;

import io.github.kl.planning_api.domain.dashboard.dto.Dashboard;
import io.github.kl.planning_api.domain.dashboard.dto.ExpenseCategorySummary;
import io.github.kl.planning_api.domain.dashboard.dto.FinancialSummary;
import io.github.kl.planning_api.domain.releases.ReleasesRepository;
import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ReleasesRepository releasesRepository;

    public Dashboard getCurrentMonthDashboard() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate firstDayMonth = currentMonth.atDay(1);
        LocalDate lastDay = currentMonth.atEndOfMonth();

        BigDecimal revenues = releasesRepository
                .sumByTypeForThePeriod(ReleaseType.REVENUE, firstDayMonth, lastDay);
        BigDecimal expenses = releasesRepository
                .sumByTypeForThePeriod(ReleaseType.EXPENSES, firstDayMonth, lastDay);
        BigDecimal balance = revenues.subtract(expenses);

        FinancialSummary financialSummary = new FinancialSummary(revenues, expenses, balance);

        List<ExpenseCategorySummary> expenseByCategory = releasesRepository
                .sumByCategoryForThePeriod(ReleaseType.EXPENSES, firstDayMonth, lastDay)
                .stream()
                .map(result ->
                        new ExpenseCategorySummary(
                                (String) result[0],
                                (BigDecimal) result[1]
                        )).toList();

        return new Dashboard(
                currentMonth.toString(),
                financialSummary,
                expenseByCategory
        );
    }

}
