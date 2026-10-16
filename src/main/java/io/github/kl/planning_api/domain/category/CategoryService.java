package io.github.kl.planning_api.domain.category;

import io.github.kl.planning_api.common.exceptions.RecordNotFoundException;
import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.common.validation.ValidationResult;
import io.github.kl.planning_api.domain.category.dto.CategoryDetails;
import io.github.kl.planning_api.domain.category.dto.CategoryForm;
import io.github.kl.planning_api.domain.category.mapper.CategoryMapper;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryValidator validator;

    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private CategoryRepository repository;

    public CategoryDetails create(CategoryForm newCategory) {
        ValidationResult result = validator.validation(newCategory);

        if(result.isInvalid()) {
            throw new ValidationException(result.getInvalidFields());
        }

        CategoryEntity entity = mapper.toEntity(newCategory);
        repository.save(entity);

        return mapper.toDetails(entity);
    }

    public Page<CategoryDetails> getList(PageRequest pageRequest) {
        return repository.findAll(pageRequest).map(mapper::toDetails);
    }

    @Transactional
    public void updateStatus(UUID id) {
       var category = repository.findById(id)
               .orElseThrow(RecordNotFoundException::new);
       category.setActive(!category.getActive());
       // repository.save(category);
    }

    public List<CategoryDetails> listCategoriesActive() {
        return repository.findByActiveTrue()
                .stream()
                .map(mapper::toDetails)
                .toList();
    }

    public List<CategoryDetails> listAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(mapper::toDetails)
                .toList();
    }
}
