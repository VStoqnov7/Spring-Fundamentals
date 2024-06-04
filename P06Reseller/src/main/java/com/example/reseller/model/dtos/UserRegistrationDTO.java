package com.example.reseller.model.dtos;

import com.example.reseller.vallidation.confirmPassword.ConfirmPasswordForm;
import com.example.reseller.vallidation.uniqueEmail.UniqueEmail;
import com.example.reseller.vallidation.uniqueUsername.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfirmPasswordForm
public class UserRegistrationDTO {

    @UniqueUsername
    @NotNull(message = "Username cannot be empty!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @UniqueEmail
    @Email(message = "Invalid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotEmpty(message = "Password cannot be empty!")
    private String confirmPassword;

}