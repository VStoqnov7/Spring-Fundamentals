package com.example.reseller.service;

import com.example.reseller.model.dtos.UserLoginDTO;
import com.example.reseller.model.dtos.UserRegistrationDTO;
import com.example.reseller.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findByUsername(String username);

    User findCurrendUser();

    void loginUser(UserLoginDTO userLoginDTO);

    void saveCurrentUser(User currentUser);

}
