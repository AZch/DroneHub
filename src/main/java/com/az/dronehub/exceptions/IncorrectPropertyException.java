package com.az.dronehub.exceptions;

import lombok.Getter;

@Getter
public class IncorrectPropertyException extends RuntimeException {

    private final String message;
    private final String fieldName;

    public IncorrectPropertyException(String property, String required, Object current) {
        this.fieldName = property;
        this.message = String.format("%s should be %s, but now %s", property, required, current);
    }
}
