package com.example.SpringBootMVC.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "email not correct")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
