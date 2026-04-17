package org.multithreadingAirport.exception;

public class CustomAirPortException extends Exception {
    public CustomAirPortException() {
    }

    public CustomAirPortException(String message) {
        super(message);
    }

    public CustomAirPortException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomAirPortException(Throwable cause) {
        super(cause);
    }

    public CustomAirPortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
