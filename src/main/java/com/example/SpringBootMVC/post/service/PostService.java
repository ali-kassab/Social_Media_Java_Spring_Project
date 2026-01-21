package com.example.SpringBootMVC.post.service;


import com.example.SpringBootMVC.post.dto.PostCreateRequest;
import com.example.SpringBootMVC.post.dto.PostResponse;
import com.example.SpringBootMVC.post.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPosts();
    String createPost(PostCreateRequest request);
    String deletePost(Long post_id);
    String updatePost(PostUpdateRequest request);
}