package com.zerorealup.codefit.core.exception;

public abstract class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException(String message) {
        super(message);
    }

    public abstract String getErrorCode();
}
