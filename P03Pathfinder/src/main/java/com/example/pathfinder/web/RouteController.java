package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.*;
import com.example.pathfinder.models.enums.Level;
import com.example.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute(name = "routeDTO")
    public RouteDTO routeAddForm() {
        return new RouteDTO();
    }

    @ModelAttribute(name = "pictureUploadDTO")
    public PictureUploadDTO pictureUploadDTO() {
        return new PictureUploadDTO();
    }

    @GetMapping("/addRoute")
    public ModelAndView addRoute(ModelAndView model){
        model.addObject("levels", Level.values());
        model.setViewName("add-route");
        return model;
    }


    @PostMapping("/addRoute")
    public ModelAndView addRoute(@Valid RouteDTO routeDTO,
                                 BindingResult bindingResult,
                                 ModelAndView model){
        if (bindingResult.hasErrors()){
            model.setViewName("add-route");
            return model;
        }
        this.routeService.saveRoute(routeDTO);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping("/routes")
    public ModelAndView routes(ModelAndView model){
        final List<AllRoutesDTO> allRoutes = this.routeService.findAllRoutes();
        model.addObject("routes", allRoutes);
        model.setViewName("routes");
        return model;
    }

    @GetMapping("/routeDetails/{routeId}")
    public ModelAndView routeDetails(@PathVariable String routeId, ModelAndView model){
        RouteDetailDTO route = this.routeService.findRouteDetailDTOById(routeId);
        if (route != null){
            model.addObject("route",route);
            model.setViewName("route-details");
            return model;
        }
        model.setViewName("routes");
        return model;
    }

}
