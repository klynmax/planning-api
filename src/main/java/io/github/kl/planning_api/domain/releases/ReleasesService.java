package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.common.exceptions.ValidationException;
import io.github.kl.planning_api.domain.card.CardRepository;
import io.github.kl.planning_api.domain.card.model.CardEntity;
import io.github.kl.planning_api.domain.category.CategoryRepository;
import io.github.kl.planning_api.domain.category.model.CategoryEntity;
import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import io.github.kl.planning_api.domain.releases.mapper.ReleasesMapper;
import io.github.kl.planning_api.domain.releases.model.ReleasesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
