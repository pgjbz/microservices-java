package com.pgjbz.hrpayroll.exception;

public class RequestTimeOutException extends RuntimeException{

    public RequestTimeOutException(String message) {
        super(message);
    }
}
