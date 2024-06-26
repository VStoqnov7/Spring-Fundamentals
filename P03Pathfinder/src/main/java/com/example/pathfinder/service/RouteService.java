package com.example.pathfinder.service;

import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.dto.RouteAllDTO;
import com.example.pathfinder.models.dto.RouteDTO;
import com.example.pathfinder.models.dto.RouteDetailDTO;
import com.example.pathfinder.models.dto.RouteMostCommentedDTO;
import com.example.pathfinder.models.enums.CategoryName;

import java.util.List;

public interface RouteService {
    void saveRoute(RouteDTO routeDTO);

    List<RouteAllDTO> findAllRoutes();

    RouteDetailDTO findRouteDetailDTOById(String routeId);

    void addComment(String routeId, String message);

    Route findRouteById(String routeId);

    RouteMostCommentedDTO findMostCommentedRouteDTO();

    List<RouteAllDTO> findRouteByCategory(CategoryName categoryPedestrian);
}
