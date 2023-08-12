package com.example.rentalcar.controllers;

import com.example.rentalcar.dtos.JwtDTO;
import com.example.rentalcar.dtos.UserDTO;
import com.example.rentalcar.dtos.UserInsertDTO;
import com.example.rentalcar.dtos.UserLoginDTO;
import com.example.rentalcar.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLoginDTO dto){
            JwtDTO jwt = service.login(dto);
            return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserInsertDTO dto){
        UserDTO user = service.register(dto);
        return ResponseEntity.ok(user);
    }
}
