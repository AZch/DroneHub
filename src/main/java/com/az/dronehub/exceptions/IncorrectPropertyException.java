package com.az.dronehub.exceptions;

public class IncorrectPropertyException extends RuntimeException {
    public IncorrectPropertyException(String property, String required, Object current) {
        super(String.format("Property %s should be %s, but now %s", property, required, current));
    }
}
