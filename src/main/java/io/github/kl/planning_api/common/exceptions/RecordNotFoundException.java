package io.github.kl.planning_api.common.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super("Registro não encontrado.");
    }
}
