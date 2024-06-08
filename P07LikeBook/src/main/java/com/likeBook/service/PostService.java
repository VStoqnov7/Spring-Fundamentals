package com.likeBook.service;

import com.likeBook.model.dtos.PostAddDTO;
import com.likeBook.model.entity.Post;

import java.util.List;

public interface PostService {
    void savePost(PostAddDTO postAddDTO);

    List<Post> findAllPosts();

    List<Post> findAllMyPosts();

    void removePost(String postId);

    void likePost(String postId);
}
