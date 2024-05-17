package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDTO {

    @NotNull
    @NotEmpty
    @Size(min = 2,message = "Name must be minimum two characters!")
    private String name;
}
