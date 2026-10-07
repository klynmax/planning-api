package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.domain.card.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {

    Optional<CardEntity> findByName(String name);

    @Query("""
        select c 
        from CardEntity c 
        where ( :id is null or c.id != :id ) 
            and c.name = :name
    """)
    List<CardEntity> findByNameAndNotId(@Param("name") String name, @Param("id") UUID id);
}
