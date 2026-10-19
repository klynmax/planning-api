package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReleasesRepository
        extends JpaRepository<ReleasesEntity, UUID>,
        JpaSpecificationExecutor<ReleasesEntity> {

    @Query(value = """
            select coalesce(sum(r.value), 0)
            from ReleasesEntity as r
            where r.type = :type
            and r.date between :init and :end
            """)
    BigDecimal sumByTypeForThePeriod(
            @Param("type") ReleaseType type,
            @Param("init") LocalDate init,
            @Param("end") LocalDate end
    );

    @Query("""
        select r.category.name, coalesce(sum(r.value), 0)
        from ReleasesEntity as r
        where r.type = :type
        and r.date between :init and :end
        group by r.category.name
        order by sum(r.value) desc
        """)
    List<Object[]> sumByCategoryForThePeriod (
            @Param("type") ReleaseType type,
            @Param("init") LocalDate init,
            @Param("end") LocalDate end
    );
}
