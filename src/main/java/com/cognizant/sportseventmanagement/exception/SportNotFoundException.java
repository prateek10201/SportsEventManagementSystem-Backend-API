package com.cognizant.sportseventmanagement.exception;

public class SportNotFoundException extends RuntimeException{

    public SportNotFoundException(String msg)
    {
        super(msg);
    }
}
