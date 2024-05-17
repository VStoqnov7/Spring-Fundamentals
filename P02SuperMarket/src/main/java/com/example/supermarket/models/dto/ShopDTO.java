package com.example.supermarket.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ShopDTO {

   @NotNull
   @NotEmpty
   @Size(min = 2, message = "Name length must be more than two characters!")
    private String address;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Name length must be more than two characters!")
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Name length must be more than two characters!")
    private String town;
}
