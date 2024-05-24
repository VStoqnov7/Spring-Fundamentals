package com.example.pathfinder.service.impl;

import com.example.pathfinder.models.User;
import com.example.pathfinder.models.UserRole;
import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.models.enums.Role;
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

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existUserByEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }


    @Override
    public boolean checkConfirmPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return false;
        }
        return true;
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
            user.getRoles().add(userRole);

        }else {
            userRole.setRole(Role.valueOf(Role.USER.name()));
            user.getRoles().add(userRole);
        }
        this.userRepository.saveAndFlush(user);
    }

}
