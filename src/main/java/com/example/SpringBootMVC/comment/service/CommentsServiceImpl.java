package com.example.SpringBootMVC.comment.service;

import com.example.SpringBootMVC.comment.dto.CreateCommentRequest;
import com.example.SpringBootMVC.comment.entity.Comment;
import com.example.SpringBootMVC.comment.dto.CommentResponse;
import com.example.SpringBootMVC.comment.repository.CommentRepository;
import com.example.SpringBootMVC.post.Repository.PostRepository;
import com.example.SpringBootMVC.post.entity.Post;
import com.example.SpringBootMVC.post.service.PostService;
import com.example.SpringBootMVC.user.entity.User;
import com.example.SpringBootMVC.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class CommentsServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private PostService postService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentsServiceImpl(PostService postService, CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Page<CommentResponse> getComments(Long postId, int page, int size) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);
        return comments.map(comment -> new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        ));
    }

    public void createComment(CreateCommentRequest request, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment(
                request.getContent(),
                user,
                post

        );
        commentRepository.save(comment);
    }

    public CommentResponse getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    public void updateComment(CreateCommentRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You donot have permission to update comment");
        }
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You donot have permission to update comment");
        }
        commentRepository.delete(comment);
    }
}
