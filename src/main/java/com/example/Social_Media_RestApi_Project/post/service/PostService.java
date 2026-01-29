package com.example.Social_Media_RestApi_Project.post.service;


import com.example.Social_Media_RestApi_Project.post.dto.PostCreateRequest;
import com.example.Social_Media_RestApi_Project.post.dto.PostResponse;
import com.example.Social_Media_RestApi_Project.post.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long postId);
    String createPost(PostCreateRequest request);
    String deletePost(Long post_id);
    String updatePost(PostUpdateRequest request);
}