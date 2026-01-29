package com.example.Social_Media_RestApi_Project.user.dto;


public class UploadResponse {
    private String message;
    private String imageUrl;

    public UploadResponse(String message, String imageUrl) {
        this.message = message;
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

