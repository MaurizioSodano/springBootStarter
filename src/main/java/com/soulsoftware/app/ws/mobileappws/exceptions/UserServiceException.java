package com.soulsoftware.app.ws.mobileappws.exceptions;

public class UserServiceException extends RuntimeException{
    private static final long serialVersionUID = 4754152779214809070L;

    public UserServiceException(String message){
        super(message);
    }
}
