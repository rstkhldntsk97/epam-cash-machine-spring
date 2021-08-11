package com.epam.springcashmachine.exception;

import com.epam.springcashmachine.model.enums.ErrorType;

public class ProductAlreadyExistsException extends ServiceException{


    private static final String DEFAULT_MESSAGE = "Product already exists!";

    public ProductAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
