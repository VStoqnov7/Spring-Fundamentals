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
}
