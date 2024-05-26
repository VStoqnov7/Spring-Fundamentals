package com.example.pathfinder.service;

import com.example.pathfinder.models.dto.UserRegistrationDTO;

public interface UserService {

    boolean existUserByEmail(String email);

    boolean checkUserName(String username);

    void saveUser(UserRegistrationDTO userRegistrationDTO);
}
