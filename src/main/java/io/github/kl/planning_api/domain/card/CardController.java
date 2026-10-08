package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cards")
@CrossOrigin("*")
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

    @GetMapping
    public Page<CardDetails> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        var pageRequest = PageRequest.of(page, size);
        return service.getList(pageRequest);
    }
}
