package com.sahce.ufcg.exceptions;

import javax.management.RuntimeOperationsException;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException(String msg){
        super(msg);
    }
}
