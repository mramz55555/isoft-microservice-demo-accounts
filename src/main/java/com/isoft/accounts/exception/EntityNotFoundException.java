package com.isoft.accounts.exception;

public class EntityNotFoundException extends EntityException {
    public EntityNotFoundException(String resource, String field, String value) {
        super(resource, field, value, "not found");
    }
}
