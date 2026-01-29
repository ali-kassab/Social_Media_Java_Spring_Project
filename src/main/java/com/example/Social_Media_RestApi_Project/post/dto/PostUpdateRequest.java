package com.example.Social_Media_RestApi_Project.post.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;


public class PostUpdateRequest {

    @NotNull
    private Long post_id;

    @Size(min = 0, max = 150)
    @Setter
    private String title;

    @Setter
    private String content;

    @NotNull
    private Long user_id;


    public Long getPost_id() {
        return post_id;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public Long getUser_id() {
        return user_id;
    }

}
