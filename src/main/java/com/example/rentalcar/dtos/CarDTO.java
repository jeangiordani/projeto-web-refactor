package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Car;
import com.example.rentalcar.entities.Status;

public class CarDTO extends CarInsertDTO {

    private Status status;
    private String image;

    public CarDTO() {
    }

    public CarDTO(String image) {
        this.image = image;
    }

    public CarDTO(Car car) {
        super(car.getId(),
                car.getDailyPrice(),
                car.getColor(),
                car.getPlate(),
                car.getModel(),
                car.getBrand(),
                car.getTotalKm(),
                car.getYear(),
                car.getSeats());
        this.image = car.getImage();
        this.status = car.getStatus();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
