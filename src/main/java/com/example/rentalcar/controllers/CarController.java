package com.example.rentalcar.controllers;

import com.example.rentalcar.dtos.CarDTO;
import com.example.rentalcar.dtos.CarInsertDTO;
import com.example.rentalcar.services.CarService;
import com.example.rentalcar.services.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private CarService carService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CarDTO> create(@Valid @RequestPart("car") CarInsertDTO dto, @RequestPart("image") MultipartFile file){
        String image = storageService.uploadImage(file);
        CarDTO carDto = carService.create(dto, image);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carDto.getId()).toUri();

        return ResponseEntity.created(uri).body(carDto);
    }
    
    @GetMapping
    public ResponseEntity<List<CarDTO>> findAll(){
        List<CarDTO> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(carService.find(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<CarDTO> update(
            @PathVariable Long id,
            @Valid @RequestPart("car") CarDTO dto,
            @RequestPart(value = "image", required = false) MultipartFile file
    ){
        String image = null;
        if (file != null){
            image = storageService.uploadImage(file);
        }

        CarDTO car = carService.update(id, dto, image);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
