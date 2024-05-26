package com.example.pathfinder.validations.registerValidation;

import com.example.pathfinder.models.dto.UserRegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegistrationDTO> {

    private String passwordField;
    private String confirmPasswordField;
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.password();
        this.confirmPasswordField = constraintAnnotation.confirmPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegistrationDTO userRegistrationDTO, ConstraintValidatorContext context) {
        String password = userRegistrationDTO.getPassword();
        String confirmPassword = userRegistrationDTO.getConfirmPassword();

        if (password != null && confirmPassword != null && password.equals(confirmPassword)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(confirmPasswordField)
                .addConstraintViolation();

        return false;
    }
}