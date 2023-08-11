package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class CarInsertDTO implements Serializable {

    private Long id;

    @NotNull(message = "Campo dailyPrice obrigatório")
    private Double dailyPrice;

    private String color;

    @NotBlank(message = "Campo plate obrigatório")
    private String plate;

    @NotBlank(message = "Campo model obrigatório")
    private String model;

    @NotBlank(message = "Campo brand obrigatório")
    private String brand;

    @Positive(message = "Campo totalKm obrigatório")
    private Integer totalKm;

    @Positive(message = "Campo year obrigatório e deve ser positivo")
    private Integer year;

    @Positive(message = "Campo seats obrigatório")
    private Integer seats;

    public CarInsertDTO() {
    }

    public CarInsertDTO(Long id, Double dailyPrice, String color, String plate, String model, String brand, Integer totalKm, Integer year, Integer seats) {
        this.id = id;
        this.dailyPrice = dailyPrice;
        this.color = color;
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.totalKm = totalKm;
        this.year = year;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Integer totalKm) {
        this.totalKm = totalKm;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
