package com.example.Social_Media_RestApi_Project.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentResponse {
    private Long id;
    @Setter
    private String content;
    @Setter
    private Long post;
    private Long userId;
    private String userName;
    private Instant createdAt;
    private Instant updatedAt;
}


