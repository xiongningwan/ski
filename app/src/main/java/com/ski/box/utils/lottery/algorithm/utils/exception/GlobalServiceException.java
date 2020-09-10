package com.ski.box.utils.lottery.algorithm.utils.exception;

public class GlobalServiceException extends Exception {
    private static final long serialVersionUID = -19363400498825725L;

    public GlobalServiceException() {
    }

   /* public GlobalServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }*/

    public GlobalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalServiceException(String message) {
        super(message);
    }

    public GlobalServiceException(Throwable cause) {
        super(cause);
    }
}
