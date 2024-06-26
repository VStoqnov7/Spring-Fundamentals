package com.likeBook.validations.login;


import com.likeBook.model.dtos.UserLoginDTO;
import com.likeBook.model.entity.User;
import com.likeBook.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginValidator implements ConstraintValidator<UserLoginForm, UserLoginDTO> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private String message;

    public UserLoginValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(UserLoginForm constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserLoginDTO userLoginDTO, ConstraintValidatorContext context) {
        User user = this.userService.findByUsername(userLoginDTO.getUsername());

        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return true;
        }

//        context.buildConstraintViolationWithTemplate(message)
//                .addPropertyNode("username")
//                .addConstraintViolation();
//        context.buildConstraintViolationWithTemplate(message)
//                .addPropertyNode("password")
//                .addConstraintViolation();
        return false;
    }
}