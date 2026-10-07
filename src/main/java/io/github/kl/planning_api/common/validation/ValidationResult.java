package io.github.kl.planning_api.common.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private List<InvalidFields> invalidFields;

    private ValidationResult(List<InvalidFields> invalidFields) {
        this.invalidFields = invalidFields;
    }

    public static ValidationResult novo() {
        return new ValidationResult(new ArrayList<>());
    }

    public void add(InvalidFields invalidField) {
        this.invalidFields.add(invalidField);
    }

    public List<InvalidFields> getInvalidFields() {
        return invalidFields;
    }

    public boolean isInvalid() {
        return !invalidFields.isEmpty();
    }
}
