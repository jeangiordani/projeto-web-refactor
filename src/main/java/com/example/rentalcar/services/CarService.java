package com.example.rentalcar.services;

import com.example.rentalcar.dtos.CarDTO;
import com.example.rentalcar.dtos.CarInsertDTO;
import com.example.rentalcar.entities.Car;
import com.example.rentalcar.entities.Status;
import com.example.rentalcar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarDTO insert(CarInsertDTO dto, String image){
        Car car = new Car();
        this.CarToCarDTO(car,dto);
        car.setImage(image);
        car.setStatus(Status.AVAILABLE);
        Car newCar = carRepository.save(car);

        return new CarDTO(newCar);
    }

    private void CarToCarDTO(Car car, CarInsertDTO dto) {
        car.setBrand(dto.getBrand());
        car.setColor(dto.getColor());
        car.setDailyPrice(dto.getDailyPrice());
        car.setModel(dto.getModel());
        car.setSeats(dto.getSeats());
        car.setPlate(dto.getPlate());
        car.setTotalKm(dto.getTotalKm());
        car.setYear(dto.getYear());
    }

}
