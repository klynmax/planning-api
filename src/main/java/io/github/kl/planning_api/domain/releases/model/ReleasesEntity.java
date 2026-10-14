package io.github.kl.planning_api.domain.releases.model;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_Releases")
public class ReleasesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReleaseType type;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal value;
}
