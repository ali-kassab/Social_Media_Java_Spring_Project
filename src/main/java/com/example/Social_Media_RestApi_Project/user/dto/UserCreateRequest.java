package com.example.Social_Media_RestApi_Project.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserCreateRequest {

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 - 50")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email not true")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 ")
    private String password;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
