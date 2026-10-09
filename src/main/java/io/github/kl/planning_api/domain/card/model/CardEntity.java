package io.github.kl.planning_api.domain.card.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbCard")
@Getter
@Setter
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "card_brand")
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "active")
    private  Boolean active = true;

    @PrePersist
    public void prePersist() {
        setRegistrationDate(LocalDateTime.now());
    }
}
