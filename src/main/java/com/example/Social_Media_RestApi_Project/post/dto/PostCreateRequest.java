package com.example.Social_Media_RestApi_Project.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
public class PostCreateRequest {

    @Setter
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 - 50 characters")
    private String title;

    @Setter
    @NotBlank(message = "Content is required")
    @Size(min = 3, max = 50, message = "Content must be between 3 - 50 characters")
    private String Content;

    @Setter
    private Long user;


}
