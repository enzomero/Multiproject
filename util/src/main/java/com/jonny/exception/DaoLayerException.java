package com.jonny.exception;

/**
 * Special class for wrapping exceptions from the DAO module.
 *
 * <p>All exceptions from DAO layer, must wrapping in that class.
 * Class helping to handle exceptions is future.
 */
public class DaoLayerException extends RuntimeException {
    /**
     * Constructor
     * @param message there is a message from original exception.
     */
    public DaoLayerException(String message){
        super(message);
    }
}
