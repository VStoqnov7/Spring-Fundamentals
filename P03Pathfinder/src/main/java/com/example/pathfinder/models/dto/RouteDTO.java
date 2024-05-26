package com.example.pathfinder.models.dto;

import com.example.pathfinder.models.enums.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO {

    private String name;

    private String description;

    private String gpxCoordinates;

    private Level level;

    private String videoUrl;

}
