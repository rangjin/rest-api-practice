package com.rangjin.restapi.advice.exception;

public class CustomEmailSigninFailedException extends RuntimeException {

    public CustomEmailSigninFailedException() {
        super();
    }

    public CustomEmailSigninFailedException(String message) {
        super(message);
    }

    public CustomEmailSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
