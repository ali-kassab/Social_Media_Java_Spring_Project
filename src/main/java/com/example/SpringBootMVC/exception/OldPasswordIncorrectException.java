package com.example.SpringBootMVC.exception;

public class OldPasswordIncorrectException extends RuntimeException {
    public OldPasswordIncorrectException() {
        super("old password is in correct");
    }
}

