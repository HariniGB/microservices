package com.eazydineapp.menu.exception;

public class GenericServiceException extends RuntimeException{
    
    private static final long serialVersionUID = 5776681206288518465L;
    
    public GenericServiceException(String message)
    {
       super(message);
    }
}