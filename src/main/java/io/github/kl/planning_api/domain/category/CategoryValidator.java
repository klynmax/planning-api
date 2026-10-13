package io.github.kl.planning_api.domain.category;

import io.github.kl.planning_api.common.validation.InvalidFields;
import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.category.dto.CategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {

    @Autowired
    private CategoryRepository repository;

    public ValidationResult validation(CategoryForm newCategory) {

        var result = ValidationResult.novo();
        var hasARegisteredCategory = repository.existsByName(newCategory.name());

        if(hasARegisteredCategory) {
            result.add(new InvalidFields("nome", "Existe uma categoria já cadastrada com este nome."));
        }

        return result;
    }
}
