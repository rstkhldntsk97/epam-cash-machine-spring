package com.epam.springcashmachine.exception;

import com.epam.springcashmachine.model.enums.ErrorType;

public class ReceiptNotExistsException extends ServiceException{

    private static final String DEFAULT_MESSAGE = "Receipt not exists";

    public ReceiptNotExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
