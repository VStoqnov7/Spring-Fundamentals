package com.dictionary.model.dto;

import com.dictionary.validation.confirmPassword.ConfirmPasswordForm;
import com.dictionary.validation.email.EmailForm;
import com.dictionary.validation.uniqueEmail.UniqueEmail;
import com.dictionary.validation.uniqueUsername.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Email cannot be empty!")
    @EmailForm
    @UniqueEmail
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotNull(message = "Password cannot be empty!")
    private String confirmPassword;

}
