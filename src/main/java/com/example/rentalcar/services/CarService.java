package com.example.rentalcar.services;

import com.example.rentalcar.dtos.CarDTO;
import com.example.rentalcar.dtos.CarInsertDTO;
import com.example.rentalcar.entities.Car;
import com.example.rentalcar.entities.Status;
import com.example.rentalcar.exceptions.ConstraintException;
import com.example.rentalcar.exceptions.DataExistsException;
import com.example.rentalcar.exceptions.DataNotFoundException;
import com.example.rentalcar.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<CarDTO> findAll(){
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(CarDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public CarDTO create(CarInsertDTO dto, String image){
        if (carRepository.existsByPlate(dto.getPlate())){
            throw new DataExistsException("Placa já existe no banco");
        }

        Car car = new Car();
        this.CarToCarDTO(car,dto);
        car.setImage(image);
        car.setStatus(Status.AVAILABLE);
        Car newCar = carRepository.save(car);

        return new CarDTO(newCar);
    }
    @Transactional
    public CarDTO update(Long id, CarDTO dto, String image) {

        try {
            Car car = carRepository.getReferenceById(id);

            if(image != null){
                car.setImage(image);
            }

            dtoToCar(dto, car);
            Car updatedCar = carRepository.save(car);

            return new CarDTO(updatedCar);
        } catch (EntityNotFoundException e){
            throw new DataNotFoundException("Carro não encontrado");
        }
    }

    private static void dtoToCar(CarDTO dto, Car car) {
        car.setStatus(dto.getStatus());
        car.setYear(dto.getYear());
        car.setTotalKm(dto.getTotalKm());
        car.setPlate(dto.getPlate());
        car.setSeats(dto.getSeats());
        car.setDailyPrice(dto.getDailyPrice());
        car.setColor(dto.getColor());
        car.setBrand(dto.getBrand());
    }

    @Transactional(readOnly = true)
    public CarDTO find(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                ()-> new DataNotFoundException("Carro não encontrado")
        );

        return new CarDTO(car);
    };

    public void delete(Long id){
        try {
            carRepository.deleteById(id);
        } catch (EntityNotFoundException e){
            throw new DataNotFoundException("Carro não encontrado");
        } catch (DataIntegrityViolationException exception){
            throw new ConstraintException("Erro ao remover devido a violação de constraint");
        }
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
