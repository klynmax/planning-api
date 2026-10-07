package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    public ResponseEntity<CardDetails> create(@RequestBody @Valid CardForm newCard) {
        CardDetails details = service.create(newCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(details);
    }

    @GetMapping("{id}")
    public ResponseEntity<CardDetails> getDetails(@PathVariable UUID id) {
        var result = service.getDetails(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody CardForm updateData) {
        service.update(id, updateData);
        return ResponseEntity.noContent().build();
    }
}
