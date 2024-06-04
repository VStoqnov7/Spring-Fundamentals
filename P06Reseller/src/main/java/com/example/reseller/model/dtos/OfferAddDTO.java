package com.example.reseller.model.dtos;

import com.example.reseller.model.enums.ConditionName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferAddDTO {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull
    @Positive(message = "The price must be a positive number")
    private double price;

    @NotNull(message = "You must select a condition!")
    private ConditionName condition;
}
