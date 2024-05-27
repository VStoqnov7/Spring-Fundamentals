package com.example.pathfinder.models.dto;

import com.example.pathfinder.models.Category;
import com.example.pathfinder.models.enums.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RouteDTO {

    @NotEmpty
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @NotEmpty
    @NotBlank
    @Size(min = 5)
    private String description;

    @NotEmpty
    @NotBlank
    private String gpxCoordinates;

    @NotNull
    private Level level;

    @NotEmpty
    @NotBlank
    private String videoUrl;

    @NotEmpty
    private List<String> categories;

    public RouteDTO() {
        this.categories = new ArrayList<>();
    }
}
