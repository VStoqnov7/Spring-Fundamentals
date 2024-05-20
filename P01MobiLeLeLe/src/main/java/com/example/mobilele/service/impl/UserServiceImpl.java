package com.example.mobilele.service.impl;

import com.example.mobilele.models.beans.LoggedUser;
import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.models.entity.User;
import com.example.mobilele.models.entity.UserRole;
import com.example.mobilele.models.enums.Role;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.service.UserService;
import com.example.mobilele.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MyValidator validator;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MyValidator validator, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        UserRole userRole = new UserRole();
        userRole.setName(this.userRepository.count() > 0 ? Role.USER : Role.ADMIN);
        if (validator.isValid(userDto)) {
            user.setCreated(LocalDateTime.now());
            user.setRole(userRole);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            this.userRepository.saveAndFlush(user);
        } else {
            validator.validate(userDto).forEach(System.out::println);

        }
    }

    @Override
    public boolean checkUserLogin(UserLoginDto userDto) {
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null && passwordEncoder.matches(userDto.getPassword(), existingUser.getPassword())) {
            existingUser.setActive(true);
            this.userRepository.saveAndFlush(existingUser);
            this.loggedUser
                    .setId(existingUser.getId())
                    .setUsername(existingUser.getUsername())
                    .setRole(existingUser.getRole().getName());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout() {
        User existingUser = userRepository.findByUsername(loggedUser.getUsername());
        existingUser.setActive(false);
        this.userRepository.saveAndFlush(existingUser);
        this.loggedUser.clearFields();
    }


    @Override
    public boolean existUser(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername()) != null;
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
