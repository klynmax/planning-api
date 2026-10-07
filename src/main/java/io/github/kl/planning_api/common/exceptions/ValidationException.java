package io.github.kl.planning_api.common.exceptions;

import io.github.kl.planning_api.common.validation.InvalidFields;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<InvalidFields> invalidFields;

    public ValidationException(List<InvalidFields> invalidFields){
        super("Erro de validação");
        this.invalidFields = invalidFields;
    }

    public List<InvalidFields> getInvalidFields() {
        return invalidFields;
    }
}
