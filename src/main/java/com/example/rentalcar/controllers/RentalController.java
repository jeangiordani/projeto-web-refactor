package com.example.rentalcar.controllers;

import com.example.rentalcar.dtos.RentalDTO;
import com.example.rentalcar.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalDTO>> findAll(){
        List<RentalDTO> rentals = rentalService.findAll();

        return ResponseEntity.ok(rentals);
    }

    @PostMapping
    public ResponseEntity<RentalDTO> create(@RequestBody RentalDTO dto){
        RentalDTO rentalDTO = rentalService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rentalDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(rentalDTO);
    }
}
