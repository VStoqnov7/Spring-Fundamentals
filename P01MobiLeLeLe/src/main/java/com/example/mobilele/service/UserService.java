package com.example.mobilele.service;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.models.dto.UserLoginDto;

public interface UserService {
    void saveUser(UserDto userDto);

    boolean checkUserLogin(UserLoginDto userDto);

    void logout();

    boolean areImported();

    boolean existUser(UserDto userDto);
}



