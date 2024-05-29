package com.example.pathfinder.service;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.dto.AllRoutesDTO;
import com.example.pathfinder.models.dto.RouteDTO;
import com.example.pathfinder.models.dto.RouteDetailDTO;

import java.util.List;

public interface RouteService {
    void saveRoute(RouteDTO routeDTO);

    List<AllRoutesDTO> findAllRoutes();

    RouteDetailDTO findRouteById(String routeId);

}
