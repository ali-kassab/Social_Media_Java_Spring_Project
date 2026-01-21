package com.example.SpringBootMVC.post.dto;

import com.example.SpringBootMVC.user.dto.UserResponse;
import com.example.SpringBootMVC.user.entity.User;

import java.time.Instant;


public class PostResponse {

    private Long id;
    private UserResponse user;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public PostResponse(Long id, UserResponse user, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public UserResponse getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
