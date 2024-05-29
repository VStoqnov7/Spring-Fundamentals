package com.example.pathfinder.models.dto;

import com.example.pathfinder.models.Picture;
import com.example.pathfinder.models.enums.Level;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteDetailDTO {

    private String authorFullName;
    private String description;
    private String gpxCoordinates;
    private String videoUrl;
    private String level;
    private List<Picture> pictures;
}
