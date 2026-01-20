package com.example.SpringBootMVC.post.dto;

import com.example.SpringBootMVC.user.entity.User;

import java.time.Instant;


public class PostResponse {

    private Long id;
    private User user;
    private String title;
    private String content;
    private Instant createdAt;

    public PostResponse(Long id, User user, String title, String content, Instant createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
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
}
