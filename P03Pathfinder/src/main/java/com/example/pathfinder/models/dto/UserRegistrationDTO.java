package com.example.pathfinder.models.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    @NotEmpty
    @NotBlank
    @Size(min = 2)
    private String username;

    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @Min(1)
    @Max(90)
    private int age;

    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String confirmPassword;
}
