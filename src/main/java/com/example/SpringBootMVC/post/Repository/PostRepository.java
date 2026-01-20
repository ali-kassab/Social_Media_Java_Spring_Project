package com.example.SpringBootMVC.post.Repository;

import com.example.SpringBootMVC.post.dto.PostResponse;
import com.example.SpringBootMVC.post.entity.Post;
import com.example.SpringBootMVC.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
}
