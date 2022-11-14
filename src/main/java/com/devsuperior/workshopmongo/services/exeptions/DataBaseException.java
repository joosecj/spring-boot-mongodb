package com.devsuperior.workshopmongo.services.exeptions;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String message) {
        super(message);
    }
}
