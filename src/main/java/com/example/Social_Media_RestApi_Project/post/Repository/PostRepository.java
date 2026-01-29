package com.example.Social_Media_RestApi_Project.post.Repository;

import com.example.Social_Media_RestApi_Project.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long post_id);
}
