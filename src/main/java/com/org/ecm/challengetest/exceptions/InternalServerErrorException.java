package com.org.ecm.challengetest.exceptions;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message){
        super(message);
    }
}
