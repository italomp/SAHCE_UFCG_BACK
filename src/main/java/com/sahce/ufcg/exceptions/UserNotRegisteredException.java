package com.sahce.ufcg.exceptions;

public class UserNotRegisteredException extends RuntimeException{
    public UserNotRegisteredException(String msg){
        super(msg);
    }
}
