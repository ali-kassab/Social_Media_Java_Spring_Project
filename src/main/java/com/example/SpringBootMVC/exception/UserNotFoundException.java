package com.example.SpringBootMVC.exception;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException() {
        super("user not found");
    }
}
