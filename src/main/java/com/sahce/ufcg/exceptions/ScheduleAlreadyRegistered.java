package com.sahce.ufcg.exceptions;

public class ScheduleAlreadyRegistered extends RuntimeException{
    public ScheduleAlreadyRegistered(String msg){
        super(msg);
    }
}
