package com.example.Social_Media_RestApi_Project.user.dto;

public class LoginResponse {
    private String token;
    private String email;
    private String name;

    public LoginResponse(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
    }
}
