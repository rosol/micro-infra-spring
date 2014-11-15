package com.ofg.infrastructure.web.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadParametersException extends RuntimeException {
    public BadParametersException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

    private List<ObjectError> errors;
}
