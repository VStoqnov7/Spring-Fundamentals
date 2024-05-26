package com.example.pathfinder.validations.loginValidation;

import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginValidator implements ConstraintValidator<UserValidateLoginForm, UserLoginDTO> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserLoginValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(UserValidateLoginForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDTO userLoginDTO, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userService.findByUsername(userLoginDTO.getUsername());

        boolean isValidPassword = passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword());
        return user.getId() != null
                && isValidPassword;
    }
}
