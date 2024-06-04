package com.example.reseller.service;

import com.example.reseller.model.dtos.UserRegistrationDTO;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);
}
