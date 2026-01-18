package com.example.SpringBootMVC.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserUpdateRequest {

    @Size(min = 3, max = 50, message = "name must be between 3 - 50")
    private String name;


    @Email(message = "email not true")
    private String email;

    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
