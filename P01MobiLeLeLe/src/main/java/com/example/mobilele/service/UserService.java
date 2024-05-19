package com.example.mobilele.service;

import com.example.mobilele.models.dto.UserDto;

public interface UserService {
    boolean saveUser(UserDto userDto);

    boolean checkUserLogin(UserDto userDto);

    void logout();
}



