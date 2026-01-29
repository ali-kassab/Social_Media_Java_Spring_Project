package com.example.Social_Media_RestApi_Project.exception;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException() {
        super("user not found");
    }
}
