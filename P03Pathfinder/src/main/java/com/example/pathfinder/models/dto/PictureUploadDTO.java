package com.example.pathfinder.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureUploadDTO {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String title;

    @NotEmpty
    @Size(min = 4)
    private String pictureUrl;
}