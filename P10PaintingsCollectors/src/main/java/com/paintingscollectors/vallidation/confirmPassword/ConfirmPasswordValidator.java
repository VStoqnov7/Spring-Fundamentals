package com.paintingscollectors.vallidation.confirmPassword;

import com.paintingscollectors.model.dto.UserRegistrationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPasswordForm, UserRegistrationDTO> {
    private String message;

    @Override
    public void initialize(ConfirmPasswordForm constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegistrationDTO userRegistrationDTO, ConstraintValidatorContext context) {
        String password = userRegistrationDTO.getPassword();
        String confirmPassword = userRegistrationDTO.getConfirmPassword();

        if (password != null && confirmPassword != null && password.equals(confirmPassword)) {
            return true;
        }

//        context.buildConstraintViolationWithTemplate(message)
//                .addPropertyNode("password")
//                .addConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("confirmPassword")
                .addConstraintViolation();

        return false;
    }
}