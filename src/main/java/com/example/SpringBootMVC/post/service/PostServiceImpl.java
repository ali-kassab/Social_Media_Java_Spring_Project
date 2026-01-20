package com.example.SpringBootMVC.post.service;

import com.example.SpringBootMVC.post.Repository.PostRepository;
import com.example.SpringBootMVC.post.dto.PostCreateRequest;
import com.example.SpringBootMVC.post.dto.PostResponse;
import com.example.SpringBootMVC.post.entity.Post;
import com.example.SpringBootMVC.user.entity.User;
import com.example.SpringBootMVC.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public  List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getUser(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt()
                )).collect(Collectors.toList());
    }

    @Override
    public String createPost(PostCreateRequest request) {

        User user = userRepository.findById(request.getUser())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = new Post(
                user,
                request.getTitle(),
                request.getContent(),
                request.getCreated_at()
        );
        postRepository.save(post);
        return "Post created successfully";
    }
}
