package io.github.kl.planning_api.domain.card;

import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.card.dto.CardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    public ResponseEntity<CardDetails> create(@RequestBody CardForm newCard) {
        CardDetails details = service.create(newCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(details);
    }
}
