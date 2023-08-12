package com.example.rentalcar.repositories;

import com.example.rentalcar.entities.Rental;
import com.example.rentalcar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByUser(User user);
}
