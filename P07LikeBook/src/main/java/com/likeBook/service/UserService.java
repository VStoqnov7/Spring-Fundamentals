package com.likeBook.service;

import com.likeBook.model.dtos.UserLoginDTO;
import com.likeBook.model.dtos.UserRegistrationDTO;
import com.likeBook.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findByUsername(String username);

    void loginUser(UserLoginDTO userLoginDTO);

}
