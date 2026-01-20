package com.example.SpringBootMVC.post.dto;

import com.example.SpringBootMVC.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;


public class PostCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 - 50 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 3, max = 50, message = "Content must be between 3 - 50 characters")
    private String Content;

    private Long user;

    private Instant created_at;


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
