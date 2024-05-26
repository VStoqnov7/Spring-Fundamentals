package com.example.pathfinder.service.impl;

import com.example.pathfinder.models.User;
import com.example.pathfinder.models.UserRole;
import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.Role;
import com.example.pathfinder.models.user.CurrentUser;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public boolean existUserByEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }


    @Override
    public boolean checkUserName(String username) {
        Optional<User> userByUsername = this.userRepository.findByUsername(username);
        if (userByUsername.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setLevel(Level.BEGINNER);
        UserRole userRole = new UserRole();

        if (userRepository.count() == 0) {
            userRole.setRole(Role.valueOf(Role.ADMIN.name()));
            user.setRole(userRole);

        }else {
            userRole.setRole(Role.valueOf(Role.USER.name()));
            user.setRole(userRole);
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.modelMapper
                .map(this.userRepository
                                .findByUsername(username)
                                .orElse(new User())
                        , User.class);
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        Optional<User> userData = this.userRepository.findByUsername(userLoginDTO.getUsername());
        this.currentUser.setUsername(userLoginDTO.getUsername());
        this.currentUser.setLoggedIn(true);
        this.currentUser.setAdmin(userData.get().getRole().getRole().name().equals("ADMIN"));
    }

    @Override
    public void logout() {
        System.out.println(currentUser.toString());
        System.out.println(currentUser.toString());
        this.currentUser.clear();
    }


}
