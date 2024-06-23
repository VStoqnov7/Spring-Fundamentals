package com.paintingscollectors.service.interfaces;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegistrationDTO;
import com.paintingscollectors.model.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findByUsername(String username);

    void loginUser(UserLoginDTO userLoginDTO);

    User findCurrendUser();

    void saveNewUser(User user);

    List<User> findAll();
}
