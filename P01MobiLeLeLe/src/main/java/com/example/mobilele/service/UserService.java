package com.example.mobilele.service;

import com.example.mobilele.models.dto.UserDto;

public interface UserService {
    boolean saveUser(UserDto userDto);

    boolean checkUser(UserDto userDto);
}



