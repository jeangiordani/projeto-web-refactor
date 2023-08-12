package com.example.rentalcar.controllers;

import com.example.rentalcar.dtos.RentalDTO;
import com.example.rentalcar.entities.User;
import com.example.rentalcar.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RentalDTO>> findAll(){
        List<RentalDTO> rentals = rentalService.findAll();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping
    public ResponseEntity<RentalDTO> create(@Valid @RequestBody RentalDTO dto){
        RentalDTO rentalDTO = rentalService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rentalDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(rentalDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<RentalDTO>> findRentalsByUser(@PathVariable Long id){
        List<RentalDTO> rentals = rentalService.findRentalByUser(id);

        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<RentalDTO>> findRentalsByLoggedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(rentalService.findRentalByUser(user.getId()));
    }
}
