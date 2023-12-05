package com.neusoft.exception;

public class LoginNameWrongException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    public LoginNameWrongException(String msg){
        super(msg);
    }
}
