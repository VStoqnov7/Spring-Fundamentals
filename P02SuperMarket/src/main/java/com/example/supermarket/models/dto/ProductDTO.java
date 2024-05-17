package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO {

    private LocalDate bestBefore;

    private String description;

    @NotNull
    @NotEmpty
    @Size(min = 2,message = "Name must be minimum two characters!")
    private String name;

    @Positive(message = "Price must a be positive number")
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Must be at least 2 characters")
    private String category;


}
