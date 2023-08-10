package com.example.rentalcar.services;

import com.example.rentalcar.dtos.RentalDTO;
import com.example.rentalcar.entities.Car;
import com.example.rentalcar.entities.Rental;
import com.example.rentalcar.entities.Status;
import com.example.rentalcar.entities.User;
import com.example.rentalcar.exceptions.CarAlreadyRentedException;
import com.example.rentalcar.exceptions.DataNotFoundException;
import com.example.rentalcar.repositories.CarRepository;
import com.example.rentalcar.repositories.RentalRepository;
import com.example.rentalcar.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RentalDTO> findAll(){
        List<Rental> rentals = rentalRepository.findAll();

        return rentals.stream().map(RentalDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public RentalDTO create(RentalDTO dto){
        try {
            Car car = carRepository.getReferenceById(dto.getCar().getId());

            if (car.getStatus() == Status.RENTED || car.getStatus() == Status.DISABLED){
                throw new CarAlreadyRentedException("Carro já foi alugado ou está indisponível");
            }

            car.setStatus(Status.RENTED);

            User user = userRepository.getReferenceById(dto.getUser().getId());

            Rental rental = new Rental();
            rental.setInitialDate(dto.getInitialDate());

            System.out.println(dto.getInitialDate());
//            System.out.println(formatStringToDate(dto.getInitialDate()));

            rental.setDeliveryDate(dto.getDeliveryDate());
            rental.setTotalPrice(dto.getTotalPrice());
            rental.setCar(car);
            rental.setUser(user);

            Rental newRental = rentalRepository.save(rental);

            return new RentalDTO(newRental);
        } catch(EntityNotFoundException e){
            throw new DataNotFoundException("Carro ou usuário não exitem");
        }
    }

    public String formatStringToDate(LocalDate date) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
