package com.jonny.exception;

public class InvalidParameterException extends RuntimeException {
    public final static String NAME = "InvalidParameterException";
    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
