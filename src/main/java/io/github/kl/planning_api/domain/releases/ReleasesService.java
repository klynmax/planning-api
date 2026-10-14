package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.card.CardRepository;
import io.github.kl.planning_api.domain.category.CategoryRepository;
import io.github.kl.planning_api.domain.releases.mapper.ReleasesMapper;
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

}
