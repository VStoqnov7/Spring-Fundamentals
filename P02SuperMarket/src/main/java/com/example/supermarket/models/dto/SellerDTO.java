package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class SellerDTO {

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

    @NotNull
    @Min(value = 18, message = "Age must be greater or equal to 18")
    private Integer age;

    @NotNull
    @Positive(message = "Salary must be a positive number")
    private BigDecimal salary;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Must be at least 2 characters")
    private String shop;
}
