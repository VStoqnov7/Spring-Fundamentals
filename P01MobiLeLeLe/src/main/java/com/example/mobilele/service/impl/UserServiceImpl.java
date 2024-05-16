package com.example.mobilele.service.impl;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.models.entity.User;
import com.example.mobilele.models.entity.UserRole;
import com.example.mobilele.models.enums.Role;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.repository.UserRoleRepository;
import com.example.mobilele.service.UserService;
import com.example.mobilele.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MyValidator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, MyValidator validator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        UserRole userRole = new UserRole();
        if (validator.isValid(userDto)) {
            if (userDto.getRole().equals("ADMIN")) {
                userRole.setName(Role.ADMIN);
            } else {
                userRole.setName(Role.USER);
            }
            user.setCreated(LocalDateTime.now());
            user.setRole(userRole);
            this.userRepository.saveAndFlush(user);
        } else {
            validator.validate(userDto).forEach(System.out::println);
        }
    }
}
