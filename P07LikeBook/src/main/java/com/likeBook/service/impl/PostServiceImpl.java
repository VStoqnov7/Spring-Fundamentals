package com.likeBook.service.impl;

import com.likeBook.model.dtos.PostAddDTO;
import com.likeBook.model.entity.Post;
import com.likeBook.model.entity.User;
import com.likeBook.repository.PostRepository;
import com.likeBook.service.MoodService;
import com.likeBook.service.PostService;
import com.likeBook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MoodService moodService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserService userService, MoodService moodService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.moodService = moodService;
    }

    @Override
    public void savePost(PostAddDTO postAddDTO) {
        Post post = modelMapper.map(postAddDTO, Post.class);
        post.setMood(this.moodService.findMoodByName(postAddDTO.getMood()));
        User user = userService.findCurrentUser();
        post.setUser(user);
        this.postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return this.postRepository.findAllByUserUsernameIsNot(this.userService.findCurrentUser().getUsername());
    }

    @Override
    public List<Post> findAllMyPosts() {
        return this.postRepository.findAllByUserUsername(this.userService.findCurrentUser().getUsername());
    }

    @Override
    public void removePost(String postId) {
        this.postRepository.deleteById(postId);
    }

    @Override
    public void likePost(String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            User user = this.userService.findCurrentUser();
            post.getUserLikes().add(user);
            postRepository.saveAndFlush(post);
        }
    }
}
