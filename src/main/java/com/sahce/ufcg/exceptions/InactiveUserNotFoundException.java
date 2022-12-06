package com.sahce.ufcg.exceptions;

public class InactiveUserNotFoundException extends RuntimeException{
    public InactiveUserNotFoundException(String msg){
        super(msg);
    }
}
