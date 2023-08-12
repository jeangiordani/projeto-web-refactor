package com.example.rentalcar.exceptions;

public class CarAlreadyRentedException extends RuntimeException{

    public CarAlreadyRentedException(String msg){
        super(msg);
    }
}
