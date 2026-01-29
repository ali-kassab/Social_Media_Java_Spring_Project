package com.example.Social_Media_RestApi_Project.exception;

public class OldPasswordIncorrectException extends RuntimeException {
    public OldPasswordIncorrectException() {
        super("old password is in correct");
    }
}

