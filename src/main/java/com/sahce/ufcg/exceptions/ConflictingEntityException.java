package com.sahce.ufcg.exceptions;

public class ConflictingEntityException extends RuntimeException{
    public ConflictingEntityException(String msg){
        super(msg);
    }
}
