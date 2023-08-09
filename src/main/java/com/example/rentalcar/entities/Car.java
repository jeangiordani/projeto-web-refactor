package com.example.rentalcar.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="cars")
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double dailyPrice;

    private String color;

    @Column(unique = true, nullable = false)
    private String plate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer totalKm;

    @Column(nullable = false)
    private Integer yearDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    private Integer seats;

    @Column(nullable = false)
    private String image;

    public Car() {
    }

    public Car(Long id, Double dailyPrice, String color, String plate, String model, String brand, Integer totalKm, Integer yearDate, Status status, Integer seats, String image) {
        this.id = id;
        this.dailyPrice = dailyPrice;
        this.color = color;
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.totalKm = totalKm;
        this.yearDate = yearDate;
        this.status = status;
        this.seats = seats;
        this.image = image;
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
        return yearDate;
    }

    public void setYear(Integer yearDate) {
        this.yearDate = yearDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
