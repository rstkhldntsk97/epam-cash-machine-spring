package com.epam.springcashmachine.exception;

import com.epam.springcashmachine.model.enums.ErrorType;

public class ReceiptUpdateException extends ServiceException{

    private static final String DEFAULT_MESSAGE = "Can not update receipt";

    public ReceiptUpdateException() {
        super(DEFAULT_MESSAGE);
    }

    public ReceiptUpdateException(String customMessage) {
        super(customMessage);
    }


    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
