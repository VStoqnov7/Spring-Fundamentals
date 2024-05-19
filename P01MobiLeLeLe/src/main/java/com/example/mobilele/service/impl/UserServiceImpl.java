package com.example.mobilele.service.impl;

import com.example.mobilele.models.dto.UserDto;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MyValidator validator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        UserRole userRole = new UserRole();
        userRole.setName(this.userRepository.count() > 0 ? Role.USER : Role.ADMIN);
        if (validator.isValid(userDto)) {
            user.setCreated(LocalDateTime.now());
            user.setRole(userRole);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            this.userRepository.saveAndFlush(user);
            return true;
        } else {
            validator.validate(userDto).forEach(System.out::println);
            return false;
        }
    }

    @Override
    public boolean checkUser(UserDto userDto) {
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null && passwordEncoder.matches(userDto.getPassword(), existingUser.getPassword())){
            existingUser.setActive(true);
            this.userRepository.saveAndFlush(existingUser);
            return true;
        }else {
            return false;
        }
    }
}
