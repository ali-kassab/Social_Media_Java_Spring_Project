package com.example.Social_Media_RestApi_Project.comment.service;

import com.example.Social_Media_RestApi_Project.comment.dto.CommentResponse;
import com.example.Social_Media_RestApi_Project.comment.dto.CreateCommentRequest;
import org.springframework.data.domain.Page;

public interface CommentService {
    public Page<CommentResponse> getComments(Long postId, int page, int size);
    public void createComment (CreateCommentRequest request , Long postId);
    public CommentResponse getCommentById(Long commentId);
    public void updateComment(CreateCommentRequest request , Long commentId);
    public void deleteComment(Long commentId);
}
