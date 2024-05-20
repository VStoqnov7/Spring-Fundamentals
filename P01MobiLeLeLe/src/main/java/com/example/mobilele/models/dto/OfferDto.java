package com.example.mobilele.models.dto;

import com.example.mobilele.models.enums.Category;
import com.example.mobilele.models.enums.Engine;
import com.example.mobilele.models.enums.Transmission;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferDto {

    @NotNull
    @NotEmpty
    private String model;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotNull
    private Engine engine;

    @NotNull
    private Transmission transmission;

    @NotNull
    @Min(1900)
    private int year;

    @NotNull
    @Min(1)
    private int mileage;

    @NotNull
    @NotEmpty
    private String imageUrl;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String description;

    @NotNull
    @NotEmpty
    private String category;

    @NotNull
    @NotEmpty
    private String brand;
}
