package com.dictionary.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    @NotNull(message = "Username cannot be empty!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}
