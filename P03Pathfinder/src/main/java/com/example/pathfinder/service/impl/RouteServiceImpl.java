package com.example.pathfinder.service.impl;

import com.example.pathfinder.models.Category;
import com.example.pathfinder.models.Comment;
import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.AllRoutesDTO;
import com.example.pathfinder.models.dto.RouteDTO;
import com.example.pathfinder.models.dto.RouteDetailDTO;
import com.example.pathfinder.models.enums.CategoryName;
import com.example.pathfinder.models.user.CurrentUser;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CommentRepository commentRepository;

    public RouteServiceImpl(RouteRepository repository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CommentRepository commentRepository) {
        this.routeRepository = repository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.commentRepository = commentRepository;
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
                .map(route -> {
                    AllRoutesDTO routeDTO = modelMapper.map(route, AllRoutesDTO.class);
                    String imageUrl = route.getPictures().stream()
                            .map(pic -> pic.getUrl())
                            .findFirst()
                            .orElse(null);
                    routeDTO.setImageUrl(imageUrl);
                    return routeDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailDTO findRouteDetailDTOById(String routeId) {
        return this.routeRepository.findById(routeId)
                .map(route -> modelMapper.map(route, RouteDetailDTO.class))
                .orElse(null);
    }

    @Override
    public void addComment(String routeId, String message) {
        Route route = this.routeRepository.findById(routeId).orElse(null);
        if (route != null && currentUser.getUsername() != null){
            User user = this.userService.findByUsername(currentUser.getUsername());
            Comment comment = new Comment();
            comment.setTextContent(message);
            comment.setAuthor(user);
            comment.setCreated(LocalDateTime.now());
            comment.setRoute(route);
            this.commentRepository.saveAndFlush(comment);
        }
    }

    @Override
    public Route findRouteById(String routeId) {
        return this.routeRepository.findById(routeId).orElse(null);
    }
}
