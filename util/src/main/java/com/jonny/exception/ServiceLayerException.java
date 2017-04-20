package com.jonny.exception;

public class ServiceLayerException extends RuntimeException {
    public final static String NAME = "ServiceLayerException";
    public ServiceLayerException(String message) {
        super(message);
    }
}
