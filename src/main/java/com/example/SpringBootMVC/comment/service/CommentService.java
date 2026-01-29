package com.example.SpringBootMVC.comment.service;

import com.example.SpringBootMVC.comment.dto.CommentResponse;
import com.example.SpringBootMVC.comment.dto.CreateCommentRequest;
import org.springframework.data.domain.Page;

public interface CommentService {
    public Page<CommentResponse> getComments(Long postId, int page, int size);
    public void createComment (CreateCommentRequest request , Long postId);
    public CommentResponse getCommentById(Long commentId);
    public void updateComment(CreateCommentRequest request , Long commentId);
    public void deleteComment(Long commentId);
}
