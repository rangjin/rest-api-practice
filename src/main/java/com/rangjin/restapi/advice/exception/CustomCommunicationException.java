package com.rangjin.restapi.advice.exception;

public class CustomCommunicationException extends RuntimeException {

    public CustomCommunicationException(String msg, Throwable t) {
        super(msg, t);
    }
    public CustomCommunicationException(String msg) {
        super(msg);
    }
    public CustomCommunicationException() {
        super();
    }

}