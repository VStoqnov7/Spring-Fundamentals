package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.dtos.StyleName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PaintingAddDTO {

    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;

    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters!")
    private String author;

    @NotBlank(message = "Please enter valid image URL!")
    @Size(max = 1500, message = "Valid image URL containing no more than 150 characters!")
    private String imageUrl;

    @NotNull(message = "You must select a style!")
    private StyleName style;
}
