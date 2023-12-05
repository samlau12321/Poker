package com.neusoft.exception;

public class FormatWrongException extends RuntimeException{
    private static final long serialVersionUID = 3L;

    public FormatWrongException(String msg){
        super(msg);
    }
}
