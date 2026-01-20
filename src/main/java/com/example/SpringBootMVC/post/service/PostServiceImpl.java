package com.example.SpringBootMVC.post.service;

import com.example.SpringBootMVC.exception.UserNotFoundException;
import com.example.SpringBootMVC.post.Repository.PostRepository;
import com.example.SpringBootMVC.post.dto.PostCreateRequest;
import com.example.SpringBootMVC.post.dto.PostResponse;
import com.example.SpringBootMVC.post.entity.Post;
import com.example.SpringBootMVC.user.UserDetailsImpl;
import com.example.SpringBootMVC.user.dto.UserResponse;
import com.example.SpringBootMVC.user.entity.User;
import com.example.SpringBootMVC.user.repository.UserRepository;
import com.example.SpringBootMVC.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private Utils utils;
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, Utils utils) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }
    @Override
    public  List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        new UserResponse(
                                post.getUser().getId(),
                                post.getUser().getName(),
                                post.getUser().getEmail()
                        ),
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

    @Override
    public String deletePost(Long id) {

        Long authID = utils.getUserIDFromAuth();
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        /*User user = userRepository.findById(post.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));*/
        postRepository.deleteById(post.getId());
        return "Post Deleted successfully";
    }
}
