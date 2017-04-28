package com.jonny.exception;

/**
 * Special class for trowing exception.
 *
 * <p>We must to throw that exception, each time when argument is invalid.
 * Class helping to handle exceptions is future.
 */
public class InvalidParameterException extends RuntimeException {
    /**
     * Constructor
     * @param message any custom message.
     */
    public InvalidParameterException(String message) {
        super(message);
    }
}
