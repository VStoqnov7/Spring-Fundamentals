package com.planner.service;

import com.planner.model.dtos.UserLoginDTO;
import com.planner.model.dtos.UserRegistrationDTO;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    void loginUser(UserLoginDTO userLoginDTO);
}
