package io.github.kl.planning_api.domain.category;

import io.github.kl.planning_api.domain.category.dto.CategoryDetails;
import io.github.kl.planning_api.domain.category.dto.CategoryForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("category")
@CrossOrigin("*")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDetails> create(@RequestBody @Valid CategoryForm newCategory) {
        CategoryDetails details = categoryService.create(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(details);
    }

    @GetMapping
    public Page<CategoryDetails> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        var pageRequest = PageRequest.of(page, size);
        return categoryService.getList(pageRequest);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<Void> UpdateStatus(@PathVariable UUID id) {
        categoryService.updateStatus(id);
        return  ResponseEntity.noContent().build();
    }

}
