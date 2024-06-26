package com.example.reseller.model.dtos;

import com.example.reseller.vallidation.login.UserLoginForm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UserLoginForm
public class UserLoginDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}