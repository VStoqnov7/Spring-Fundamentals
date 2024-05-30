package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.AllRoutesDTO;
import com.example.pathfinder.models.dto.RouteDetailDTO;
import com.example.pathfinder.models.dto.RouteMostCommentedDTO;
import com.example.pathfinder.models.enums.CategoryName;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.pathfinder.constraints.Categories.*;

@Controller
@RequestMapping("/")
public class HomeController {

    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute("mostCommentedRoute")
    public RouteMostCommentedDTO mostCommentedDTO() {
        return new RouteMostCommentedDTO();
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        RouteMostCommentedDTO route = this.routeService.findMostCommentedRouteDTO();
        model.addObject("mostCommentedRoute", route);
        model.setViewName("index");
        return model;
    }

    @GetMapping("/about")
    public ModelAndView about(ModelAndView model){
        model.setViewName("about");
        return model;
    }

    @GetMapping("/pedestrian")
    public ModelAndView pedestrian(ModelAndView model){
        final List<AllRoutesDTO> routes =
                this.routeService.findRouteByCategory(CategoryName.valueOf(CATEGORY_PEDESTRIAN));
        model.addObject("routesCategory", routes);
        model.setViewName("pedestrian");
        return model;
    }

    @GetMapping("/bicycle")
    public ModelAndView bicycle(ModelAndView model){
        final List<AllRoutesDTO> routes =
                this.routeService.findRouteByCategory(CategoryName.valueOf(CATEGORY_BICYCLE));
        model.addObject("routesCategory", routes);
        model.setViewName("bicycle");
        return model;
    }

    @GetMapping("/motorcycle")
    public ModelAndView motorcycle(ModelAndView model){
        final List<AllRoutesDTO> routes =
                this.routeService.findRouteByCategory(CategoryName.valueOf(CATEGORY_MOTORCYCLE));
        model.addObject("routesCategory", routes);
        model.setViewName("motorcycle");
        return model;
    }

    @GetMapping("/car")
    public ModelAndView car(ModelAndView model){
        final List<AllRoutesDTO> routes =
                this.routeService.findRouteByCategory(CategoryName.valueOf(CATEGORY_CAR));
        model.addObject("routesCategory", routes);
        model.setViewName("car");
        return model;
    }



}
