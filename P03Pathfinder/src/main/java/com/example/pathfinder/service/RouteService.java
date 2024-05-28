package com.example.pathfinder.service;

import com.example.pathfinder.models.dto.AllRoutesDTO;
import com.example.pathfinder.models.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    void saveRoute(RouteDTO routeDTO);

    List<AllRoutesDTO> findAllRoutes();
}
