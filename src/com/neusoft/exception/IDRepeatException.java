package com.neusoft.exception;

public class IDRepeatException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public IDRepeatException(String msg) {
        super(msg);
    }
}
