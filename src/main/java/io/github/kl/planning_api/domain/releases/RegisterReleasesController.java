package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.card.CardService;
import io.github.kl.planning_api.domain.card.dto.CardDetails;
import io.github.kl.planning_api.domain.category.CategoryService;
import io.github.kl.planning_api.domain.category.dto.CategoryDetails;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("releases")
public class RegisterReleasesController {

    @Autowired
    private ReleasesService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReleasesDetail create(@RequestBody @Valid ReleasesForm form) {
        return service.create(form);
    }

    @GetMapping("available-categories")
    public List<CategoryDetails> getListAvailableCategories() {
        return categoryService.listCategoriesActive();
    }

    @GetMapping("available-cards")
    public List<CardDetails> getListAvailableCards() {
        return cardService.listCardActive();
    }

}
