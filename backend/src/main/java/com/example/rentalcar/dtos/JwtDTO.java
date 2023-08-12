package com.example.rentalcar.dtos;

import java.io.Serializable;

public class JwtDTO implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public JwtDTO(String token) {
        this.token = token;
    }
}
