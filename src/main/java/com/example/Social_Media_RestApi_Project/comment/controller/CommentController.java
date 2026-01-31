package com.example.Social_Media_RestApi_Project.comment.controller;

import com.example.Social_Media_RestApi_Project.comment.dto.CommentResponse;
import com.example.Social_Media_RestApi_Project.comment.dto.CreateCommentRequest;
import com.example.Social_Media_RestApi_Project.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;
 /*git test*/
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getComments(@PathVariable Long postId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<CommentResponse> comments = commentService.getComments(postId, page, size);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<String> createComment(@Valid @RequestBody CreateCommentRequest request, @PathVariable Long postId) {
        commentService.createComment(request, postId);
        return ResponseEntity.ok("Successfully created comment");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long postId ,@PathVariable Long id ){
        CommentResponse commentResponse = commentService.getCommentById(id);
        return ResponseEntity.ok(commentResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> upadateComment(@PathVariable Long postId, @PathVariable Long id ,@RequestBody CreateCommentRequest request){
        commentService.updateComment(request,id);
        return ResponseEntity.ok("Successfully updated comment");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long id ){
        commentService.deleteComment(id);
        return ResponseEntity.ok("Successfully deleted comment");
    }
}
