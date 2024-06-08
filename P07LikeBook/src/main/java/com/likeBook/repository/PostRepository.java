package com.likeBook.repository;

import com.likeBook.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByUserUsernameIsNot(String username);
    List<Post> findAllByUserUsername(String username);
}