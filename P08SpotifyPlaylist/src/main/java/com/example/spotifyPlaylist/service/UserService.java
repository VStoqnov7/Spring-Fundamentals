package com.example.spotifyPlaylist.service;

import com.example.spotifyPlaylist.model.dtos.UserLoginDTO;
import com.example.spotifyPlaylist.model.dtos.UserRegistrationDTO;
import com.example.spotifyPlaylist.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findByUsername(String username);

    User findCurrendUser();

    void loginUser(UserLoginDTO userLoginDTO);

}

