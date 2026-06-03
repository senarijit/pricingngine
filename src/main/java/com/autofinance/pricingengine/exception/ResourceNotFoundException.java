package com.autofinance.pricingengine.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String fieldName;
    private final String fieldValue;

    public ResourceNotFoundException(String message, String fieldName, String fieldValue) {
        super(message);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
