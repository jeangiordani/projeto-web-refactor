package com.example.rentalcar.repositories;

import com.example.rentalcar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrCpfOrCnh(String email, String cpf, Integer cnh);

    Optional<User> findByEmail(String email);
}
