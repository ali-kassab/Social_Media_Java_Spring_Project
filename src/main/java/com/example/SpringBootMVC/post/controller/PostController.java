package com.example.SpringBootMVC.post.controller;

import com.example.SpringBootMVC.post.dto.PostCreateRequest;
import com.example.SpringBootMVC.post.dto.PostResponse;
import com.example.SpringBootMVC.post.dto.PostUpdateRequest;
import com.example.SpringBootMVC.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long post_id) {
        return ResponseEntity.ok(postService.getPostById(post_id));
    }

    @PostMapping
    public ResponseEntity<String> createPost(
            @Valid
            @RequestBody
            PostCreateRequest request
    ) {
        String response = postService.createPost(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable Long post_id) {
        String response = postService.deletePost(post_id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<String> updatePost(
            @Valid
            @RequestBody
            PostUpdateRequest request){
        String response = postService.updatePost(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
