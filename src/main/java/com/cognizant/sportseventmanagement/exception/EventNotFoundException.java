package com.cognizant.sportseventmanagement.exception;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(String msg)
    {
        super(msg);
    }
}
