package com.isoft.accounts.exception;

public class EntityException extends RuntimeException {
    public EntityException(String resource, String field, String value, String message) {
        super(resource + " with " + field + "=" + value + " " + message);
    }
}