package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class ReleasesSpecs {
    private ReleasesSpecs() {}

    public static Specification<ReleasesEntity> typeEqual(ReleaseType type) {
        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<ReleasesEntity> categoryEqual(UUID categoryId) {
        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<ReleasesEntity> monthEqual(YearMonth month) {
        return (root, query, criteriaBuilder) -> {
            LocalDate initMonth = month.atDay(1);
            LocalDate endMonth = month.atEndOfMonth();

            return criteriaBuilder.between(root.get("date"), initMonth, endMonth);
        };
    }
}
