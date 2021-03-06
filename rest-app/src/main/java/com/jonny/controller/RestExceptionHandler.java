package com.jonny.controller;

import com.jonny.exception.DaoLayerException;
import com.jonny.exception.InvalidParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DaoLayerException.class)
    public @ResponseBody
    String handlerDaoLayerException(DaoLayerException ex){
        return "DataAccessException: " + ex.getLocalizedMessage();
    }

    @ExceptionHandler(InvalidParameterException.class)
    public @ResponseBody
    String handlerInvalidParameterException(InvalidParameterException ex){
        return "InvalidParameterException: " + ex.getLocalizedMessage();
    }
}
