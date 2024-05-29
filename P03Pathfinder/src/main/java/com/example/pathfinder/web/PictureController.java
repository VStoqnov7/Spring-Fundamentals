package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.PictureUploadDTO;
import com.example.pathfinder.models.dto.RouteDetailDTO;
import com.example.pathfinder.service.PictureService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PictureController {

    private final PictureService pictureService;
    private final RouteService routeService;
    private final UserService userService;

    public PictureController(PictureService pictureService, RouteService routeService, UserService userService) {
        this.pictureService = pictureService;
        this.routeService = routeService;
        this.userService = userService;
    }

    @ModelAttribute("route")
    public RouteDetailDTO getRoute(@PathVariable String routeId) {
        return routeService.findRouteDetailDTOById(routeId);
    }

    @PostMapping("/uploadPicture/{routeId}")
    public ModelAndView uploadPicture(@PathVariable String routeId, @Valid PictureUploadDTO pictureUploadDTO, BindingResult bindingResult, ModelAndView model) {
        boolean userIsLogin = this.userService.isLogin();
        if (bindingResult.hasErrors() || userIsLogin) {
            model.addObject("userIsLogin", userIsLogin);
            model.addObject("errors", bindingResult.hasErrors() && !userIsLogin);
            model.setViewName("route-details");
            return model;
        }

        this.pictureService.uploadPicture(routeId, pictureUploadDTO);
        model.setViewName("redirect:/routeDetails/" + routeId);
        return model;
    }
}
