package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Rental;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class RentalDTO implements Serializable {

    private Long id;
    private LocalDate initialDate;
    private LocalDate deliveryDate;
    private Double totalPrice;
    private CarDTO car;
    private UserDTO user;

    public RentalDTO() {
    }

    public RentalDTO(Long id, LocalDate initialDate, LocalDate deliveryDate, Double totalPrice, CarDTO car, UserDTO user) {
        this.id = id;
        this.initialDate = initialDate;
        this.deliveryDate = deliveryDate;
        this.totalPrice = totalPrice;
        this.car = car;
        this.user = user;
    }

    public RentalDTO(Rental rental) {
        this.id = rental.getId();
        this.initialDate = rental.getInitialDate();
        this.deliveryDate = rental.getDeliveryDate();
        this.totalPrice = rental.getTotalPrice();
        this.car = new CarDTO(rental.getCar());
        this.user = new UserDTO(rental.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
