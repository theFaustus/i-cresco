package com.evil.inc.icresco.domain.exception;

import java.util.Arrays;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, String fieldName, String fieldValue) {
        super(String.format("Entity %s with %s = [%s] not found", clazz.getSimpleName(), fieldName, fieldValue));
    }

    public NotFoundException(Class<?> clazz, String[] fieldNames, String[] fieldValues) {
        super(String.format("Entity %s with %s = [%s] not found", clazz.getSimpleName(), Arrays.toString(fieldNames),
                            Arrays.toString(fieldValues)));
    }

}