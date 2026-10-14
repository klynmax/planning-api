package io.github.kl.planning_api.domain.releases;

import io.github.kl.planning_api.domain.releases.dto.ReleasesDetail;
import io.github.kl.planning_api.domain.releases.dto.ReleasesForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("releases")
public class RegisterReleasesController {

    @Autowired
    private ReleasesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReleasesDetail create(@RequestBody @Valid ReleasesForm form) {
        return service.create(form);
    }

}
