package com.jonny.exception;

public class DaoLayerException extends RuntimeException {
    public final static String NAME = "DaoLayerException";
    public DaoLayerException(String message){
        super(message);
    }


}
