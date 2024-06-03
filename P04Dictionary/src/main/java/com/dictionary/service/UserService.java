package com.dictionary.service;

import com.dictionary.model.dto.UserLoginDTO;
import com.dictionary.model.dto.UserRegistrationDTO;
import com.dictionary.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    User findByUsername(String username);

    User findCurrendUser();

    void loginUser(UserLoginDTO userLoginDTO);

}
