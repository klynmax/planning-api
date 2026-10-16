package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.domain.card.CardRepository;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.CategoryRepository;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import io.github.kl.planning_api.domain.releases.mapper.ReleasesMapper;
import io.github.kl.planning_api.domain.releases.model.ReleaseType;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

import static io.github.kl.planning_api.domain.releases.ReleasesSpecs.*;

@Service
public class ReleasesService {

    @Autowired
    private ReleasesRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ReleasesValidation validator;
    @Autowired
    private ReleasesMapper mapper;

    public ReleasesDetail create(ReleasesForm form) {
        CategoryEntity category = categoryRepository
                .findById(form.categoryId())
                .orElse(null);

        CardEntity card = null;
        if(form.cardId() != null) {
            card = cardRepository.findById(form.cardId()).orElse(null);
        }

        var result = validator.validation(form, category, card);

        if(result.isInvalid()){
            throw new ValidationException(result.getInvalidFields());
        }

        ReleasesEntity entity = mapper.toEntity(form, category, card);
        repository.save(entity);

        return mapper.toDetails(entity);
    }

    public Page<ReleasesDetail> getList(
            PageRequest pageRequest,
            YearMonth date,
            ReleaseType type,
            UUID categoryId
    ) {
        Specification<ReleasesEntity> spec = Specification.unrestricted();

        if(type != null) {
            spec = spec.and(typeEqual(type));
        }

        if(categoryId != null) {
            spec = spec.and(categoryEqual(categoryId));
        }

        if(date != null) {
            spec = spec.and(monthEqual(date));
        }

        var result = repository.findAll(spec, pageRequest);

        return result.map(mapper::toDetails);
    }

}
