package com.paintingscollectors.model.dto;

import com.paintingscollectors.vallidation.confirmPassword.ConfirmPasswordForm;
import com.paintingscollectors.vallidation.emai.UniqueEmail;
import com.paintingscollectors.vallidation.uniqueUsername.UniqueUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@ConfirmPasswordForm
public class UserRegistrationDTO {

    @NotNull
    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @UniqueEmail
    @Email(message = "Invalid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;

}