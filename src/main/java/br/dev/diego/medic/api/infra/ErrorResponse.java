package br.dev.diego.medic.api.infra;

import org.springframework.validation.FieldError;

public record ErrorResponse(String fieldError, String fieldMessage) {

    public ErrorResponse(FieldError errors) {
        this(errors.getField(), errors.getDefaultMessage());
    }
}
