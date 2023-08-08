package com.example.rentalcar.controllers;

import com.example.rentalcar.dtos.CarInsertDTO;
import com.example.rentalcar.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private FileStorageService storageService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CarInsertDTO> teste(@RequestPart("car") CarInsertDTO dto, @RequestPart("image") MultipartFile file){

        System.out.println(storageService.uploadImage(file));
        return ResponseEntity.ok(dto);
    }
}
