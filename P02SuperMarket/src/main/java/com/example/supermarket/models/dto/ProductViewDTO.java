package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductViewDTO {

    private String name;

    private BigDecimal price;
}
