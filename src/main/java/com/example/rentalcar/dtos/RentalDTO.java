package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Rental;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Date;

public class RentalDTO implements Serializable {

    private Long id;

    @NotNull(message = "Campo initialDate obrigátorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date initialDate;

    @NotNull(message = "Campo deliveryDate obrigátorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date deliveryDate;

    @NotNull(message = "Campo totalPrice obrigatório")
    @Positive(message = "Campo totalPrice deve ser positivo")
    private Double totalPrice;

    @NotNull(message = "Campo car obrigatório")
    private CarDTO car;

    @NotNull(message = "Campo user obrigatório")
    private UserDTO user;

    public RentalDTO() {
    }

    public RentalDTO(Long id, Date initialDate, Date deliveryDate, Double totalPrice, CarDTO car, UserDTO user) {
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

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
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
