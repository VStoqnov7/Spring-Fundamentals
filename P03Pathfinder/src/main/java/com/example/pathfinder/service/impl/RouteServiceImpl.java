package com.example.pathfinder.service.impl;

import com.example.pathfinder.models.Category;
import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.AllRoutesDTO;
import com.example.pathfinder.models.dto.RouteDTO;
import com.example.pathfinder.models.enums.CategoryName;
import com.example.pathfinder.models.user.CurrentUser;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;

    public RouteServiceImpl(RouteRepository repository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.routeRepository = repository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void saveRoute(RouteDTO routeDTO) {
        List<Category> categoryEnums = routeDTO.getCategories().stream()
                .map(category -> new Category(CategoryName.valueOf(category)))
                .collect(Collectors.toList());

        Route route = modelMapper.map(routeDTO, Route.class);
        route.setCategories(categoryEnums);
        User user = this.userService.findByUsername(currentUser.getUsername());
        route.setAuthor(user);
        this.routeRepository.saveAndFlush(route);
    }

    @Override
    public List<AllRoutesDTO> findAllRoutes() {
        List<Route> allRoutes = this.routeRepository.findAll();

        return allRoutes.stream()
                .map(route -> this.modelMapper.map(route, AllRoutesDTO.class))
                .collect(Collectors.toList());
    }
}
