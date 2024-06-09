package com.example.spotifyPlaylist.model.dtos;

import com.example.spotifyPlaylist.validations.confirmPassword.ConfirmPasswordForm;
import com.example.spotifyPlaylist.validations.uniqueEmail.UniqueEmail;
import com.example.spotifyPlaylist.validations.uniqueUsername.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfirmPasswordForm
public class UserRegistrationDTO {

    @NotNull
    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @UniqueEmail
    @Email(message = "Invalid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    private String confirmPassword;

}