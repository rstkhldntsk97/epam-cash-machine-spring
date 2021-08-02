package com.epam.springcashmachine.model;

import java.time.LocalDateTime;

import com.epam.springcashmachine.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;

}
