package com.epam.springcashmachine.exception;

import com.epam.springcashmachine.model.enums.ErrorType;

public class ProductNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Product already exists!";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
