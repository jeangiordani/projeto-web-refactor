package com.example.rentalcar.services;

import com.example.rentalcar.dtos.JwtDTO;
import com.example.rentalcar.dtos.UserDTO;
import com.example.rentalcar.dtos.UserInsertDTO;
import com.example.rentalcar.dtos.UserLoginDTO;
import com.example.rentalcar.entities.Role;
import com.example.rentalcar.entities.User;
import com.example.rentalcar.exceptions.DataNotFoundException;
import com.example.rentalcar.exceptions.UserExistsException;
import com.example.rentalcar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO register(UserInsertDTO dto){
        boolean userExists = userRepository.existsByEmailOrCpfOrCnh(dto.getEmail(), dto.getCpf(), dto.getCnh());
        if(userExists){
            throw new UserExistsException("Usuário já existe");
        }

        User user = userToUserDTO(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);

        return new UserDTO(user);
    }

    public JwtDTO login(UserLoginDTO dto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new DataNotFoundException("Usuário não encontrado")
        );

        var jwt = jwtService.generateToken(user);

        return new JwtDTO(jwt);
    }

    private User userToUserDTO(UserInsertDTO dto){
        return new User(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getBirthday(),
                dto.getCpf(),
                dto.getCnh(),
                dto.getPhoneNumber(),
                Role.USER);
    }
}
