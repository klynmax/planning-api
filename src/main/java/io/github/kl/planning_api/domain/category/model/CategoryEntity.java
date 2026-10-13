package io.github.kl.planning_api.domain.category.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_Category")
@Getter@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String name;
    @Column
    private Boolean active;

    @PrePersist
    public void prePersist() {
        setActive(true);
    }
}
