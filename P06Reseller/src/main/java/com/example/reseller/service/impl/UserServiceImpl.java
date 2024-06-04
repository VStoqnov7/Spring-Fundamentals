package com.example.reseller.service.impl;

import com.example.reseller.model.dtos.UserLoginDTO;
import com.example.reseller.model.dtos.UserRegistrationDTO;
import com.example.reseller.model.entity.User;
import com.example.reseller.model.user.CurrentUser;
import com.example.reseller.repository.UserRepository;
import com.example.reseller.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, CurrentUser currentUser) {
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
        return this.userRepository.findByUsername(currentUser.getUsername());
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        this.currentUser.setUsername(userLoginDTO.getUsername());
        this.currentUser.setLoggedIn(true);
    }
}
