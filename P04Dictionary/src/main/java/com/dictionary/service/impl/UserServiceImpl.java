package com.dictionary.service.impl;

import com.dictionary.model.dto.UserLoginDTO;
import com.dictionary.model.dto.UserRegistrationDTO;
import com.dictionary.model.entity.User;
import com.dictionary.model.user.CurrentUser;
import com.dictionary.repo.UserRepository;
import com.dictionary.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void saveUser(UserRegistrationDTO userRegistrationDTO) {
        User user = this.modelMapper.map(userRegistrationDTO,User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findCurrendUser() {
        return this.userRepository.findByUsername(this.currentUser.getUsername());
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        this.currentUser.setUsername(userLoginDTO.getUsername());
        this.currentUser.setLoggedIn(true);
    }
}
