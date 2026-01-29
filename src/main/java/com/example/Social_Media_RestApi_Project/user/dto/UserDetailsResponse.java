package com.example.Social_Media_RestApi_Project.user.dto;


import java.time.Instant;

public class UserDetailsResponse extends UserResponse {
    private Instant createdAt;
    private Instant updatedAt;

  public  UserDetailsResponse(Long id, String name, String email, String profileImage, Instant createdAt, Instant updatedAt) {
        super(id, name, email, profileImage);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
