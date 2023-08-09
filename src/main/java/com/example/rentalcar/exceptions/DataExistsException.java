package com.example.rentalcar.exceptions;

public class DataExistsException extends RuntimeException{

    public DataExistsException(String msg){
        super(msg);
    }
}
