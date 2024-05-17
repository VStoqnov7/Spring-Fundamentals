package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TownDTO {
    @NotEmpty
    private String name;
}
