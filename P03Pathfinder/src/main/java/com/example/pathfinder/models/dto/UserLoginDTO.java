package com.example.pathfinder.models.dto;

import com.example.pathfinder.validations.loginValidation.UserValidateLoginForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserValidateLoginForm
public class UserLoginDTO {

    @NotEmpty
    @NotBlank
    @Size(min = 2)
    private String username;
    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
}
