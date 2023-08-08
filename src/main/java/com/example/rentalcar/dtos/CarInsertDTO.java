package com.example.rentalcar.dtos;

import com.example.rentalcar.entities.Status;

import java.io.Serializable;

public class CarInsertDTO implements Serializable {

    private Long id;

    private Double dailyPrice;

    private String color;

    private String plate;

    private String model;

    private String brand;

    private Integer totalKm;

    private Integer year;

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
