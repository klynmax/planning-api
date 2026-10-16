package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.category.CategoryService;
import io.github.kl.planning_api.domain.category.dto.CategoryDetails;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("releases")
public class ListingReleaseController {

    @Autowired
    private ReleasesService service;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<ReleasesDetail> getList(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "category-id", required = false) UUID categoryId
            ) {
        var pageRequest = PageRequest.of(page, size);
        var monthYear = parseMonth(month);
        var typeReleasesSelect = parseType(type);

        return  service.getList(pageRequest, monthYear, typeReleasesSelect, categoryId);
    }

    @GetMapping("release-list-all")
    public List<CategoryDetails> listAll() {
        return categoryService.listAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    private ReleaseType parseType(String type) {
        if(type == null || type.isBlank()) {
            return null;
        }

        try {
            return ReleaseType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parâmetro 'tipo' inválido, valores aceitos: 'REVENUE' ou 'EXPENSES'.");
        }
    }

    private YearMonth parseMonth(String month) {
        if(month == null || month.isBlank()) {
            return null;
        }

        try {
            return YearMonth.parse(month);
        } catch (DateTimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parâmetro 'mes' inválido, use o formato yyyy-MM");
        }
    }
}
