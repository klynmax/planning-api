package io.github.kl.planning_api.domain.releases.model;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_releases")
@Getter@Setter
public class ReleasesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReleaseType type;

    @Column(name = "release_date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;
}
