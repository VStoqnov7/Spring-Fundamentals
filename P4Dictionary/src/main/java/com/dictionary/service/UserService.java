package com.dictionary.service;

import com.dictionary.model.dto.UserRegistrationDTO;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);
}
