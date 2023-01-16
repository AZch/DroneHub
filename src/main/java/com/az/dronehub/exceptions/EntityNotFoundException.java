package com.az.dronehub.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
