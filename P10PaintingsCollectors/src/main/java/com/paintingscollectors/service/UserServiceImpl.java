package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegistrationDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.user.CurrentUser;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(CurrentUser currentUser, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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
    public void loginUser(UserLoginDTO userLoginDTO) {
        this.currentUser.setUsername(userLoginDTO.getUsername());
        this.currentUser.setLoggedIn(true);
    }

    @Override
    public User findCurrendUser() {
        return this.userRepository.findByUsername(currentUser.getUsername());
    }

    @Override
    public void saveNewUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
