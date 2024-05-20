package com.example.mobilele.service;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.models.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    boolean checkUserLogin(UserLoginDto userDto);

    void logout();

    boolean existUser(UserDto userDto);

    User getUserById(String id);
}



