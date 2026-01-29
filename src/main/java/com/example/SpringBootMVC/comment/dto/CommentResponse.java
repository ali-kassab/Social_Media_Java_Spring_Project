package com.example.SpringBootMVC.comment.dto;

import com.example.SpringBootMVC.post.entity.Post;
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


