package com.isoft.accounts.exception;

import lombok.Generated;

import java.util.ArrayList;
import java.util.List;

public class ModelException extends RuntimeException {
    private final List<RuntimeException> errors;

    public ModelException() {
        this("");
    }

    public ModelException(String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public void addError(RuntimeException e) {
        this.errors.add(e);
    }

    public void throwIfHasError() {
        if (this.errors.size() > 0) {
            throw new ModelException("Errors=" + this.errors);
        }
    }

    @Generated
    public List<RuntimeException> getErrors() {
        return this.errors;
    }
}
