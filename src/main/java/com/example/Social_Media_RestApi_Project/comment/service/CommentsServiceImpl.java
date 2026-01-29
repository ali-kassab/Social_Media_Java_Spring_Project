package com.example.Social_Media_RestApi_Project.comment.service;

import com.example.Social_Media_RestApi_Project.comment.dto.CreateCommentRequest;
import com.example.Social_Media_RestApi_Project.comment.entity.Comment;
import com.example.Social_Media_RestApi_Project.comment.dto.CommentResponse;
import com.example.Social_Media_RestApi_Project.comment.repository.CommentRepository;
import com.example.Social_Media_RestApi_Project.post.Repository.PostRepository;
import com.example.Social_Media_RestApi_Project.post.entity.Post;
import com.example.Social_Media_RestApi_Project.post.service.PostService;
import com.example.Social_Media_RestApi_Project.user.entity.User;
import com.example.Social_Media_RestApi_Project.user.repository.UserRepository;
import com.example.Social_Media_RestApi_Project.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class CommentsServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final Utils utils;

    public CommentsServiceImpl(PostService postService, CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository ,Utils utils) {
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.utils = utils;
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
        String email = utils.getUserAuthData().getEmail();
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
        if (utils.isAuthorizedForThis(comment.getUser().getId())) {
            throw new RuntimeException("You do not have permission to update comment");
        }
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if (utils.isAuthorizedForThis(comment.getUser().getId())) {
            throw new RuntimeException("You do not have permission to delete comment");
        }
        commentRepository.delete(comment);
    }
}
