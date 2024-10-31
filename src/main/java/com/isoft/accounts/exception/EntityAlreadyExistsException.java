package com.isoft.accounts.exception;

public class EntityAlreadyExistsException extends EntityException {
    public EntityAlreadyExistsException(String resource, String field, String value) {
        super(resource, field, value, "already exists");
    }
}
