package com.payment.pollen.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionConverter
{
    public static final String NO_EMAIL = "Email needs to be provided";
    public static final String NO_PASSWORD = "Password needs to be provided";
    public static final String WRONG_PASSWORD = "";


    public static ResponseEntity<?> convertException(Exception exception)
    {
        ResponseEntity output = null;
        switch (exception.getMessage())
        {
            case ExceptionConverter.NO_EMAIL :
            case ExceptionConverter.NO_PASSWORD :
                output = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(exception.getMessage());
                break;

            case ExceptionConverter.WRONG_PASSWORD:
                output = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(exception.getMessage());
                break;
            default:
                output = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                            .body(exception.getMessage());
                break;
        }

        return output;
    }

}
