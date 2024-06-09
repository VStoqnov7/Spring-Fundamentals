package com.example.spotifyPlaylist.service.impl;

import com.example.spotifyPlaylist.repository.UserRepository;
import com.example.spotifyPlaylist.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
