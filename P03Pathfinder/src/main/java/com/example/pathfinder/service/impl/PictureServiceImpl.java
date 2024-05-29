package com.example.pathfinder.service.impl;

import com.example.pathfinder.models.Picture;
import com.example.pathfinder.models.Route;
import com.example.pathfinder.models.User;
import com.example.pathfinder.models.dto.PictureUploadDTO;
import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.PictureService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final RouteService routeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, RouteService routeService, UserService userService, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.routeService = routeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void uploadPicture(String routeId, PictureUploadDTO pictureUploadDTO) {
        Route route = this.routeService.findRouteById(routeId);
        User user = this.userService.findCurrentUser();
        Picture picture = modelMapper.map(pictureUploadDTO, Picture.class);
        picture.setAuthor(user);
        picture.setRoute(route);
        this.pictureRepository.saveAndFlush(picture);
    }
}
