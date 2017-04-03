package com.jonny.exception;

public class DaoLayerException extends RuntimeException {
    public DaoLayerException(String message){
        super(message);
    }
}
