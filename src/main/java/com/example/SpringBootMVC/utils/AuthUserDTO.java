package com.example.SpringBootMVC.utils;

import lombok.Getter;

import java.util.List;

@Getter
public class AuthUserDTO {

    private Long id;
    private String email;
    private List<String> roles;

    // constructor
    public AuthUserDTO(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
