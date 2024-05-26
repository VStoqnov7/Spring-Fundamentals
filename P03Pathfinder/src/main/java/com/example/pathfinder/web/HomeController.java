package com.example.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/about")
    public ModelAndView about(ModelAndView model){
        model.setViewName("about");
        return model;
    }

    @GetMapping("/addRoute")
    public ModelAndView addRoute(ModelAndView model){
        model.setViewName("add-route");
        return model;
    }


    @GetMapping("/pedestrian")
    public ModelAndView pedestrian(ModelAndView model){
        model.setViewName("pedestrian");
        return model;
    }

    @GetMapping("/bicycle")
    public ModelAndView bicycle(ModelAndView model){
        model.setViewName("bicycle");
        return model;
    }

    @GetMapping("/motorcycle")
    public ModelAndView motorcycle(ModelAndView model){
        model.setViewName("motorcycle");
        return model;
    }

    @GetMapping("/car")
    public ModelAndView car(ModelAndView model){
        model.setViewName("car");
        return model;
    }

    @GetMapping("/routeDetails")
    public ModelAndView routeDetails(ModelAndView model){
        model.setViewName("route-details");
        return model;
    }

    @GetMapping("/routes")
    public ModelAndView routes(ModelAndView model){
        model.setViewName("routes");
        return model;
    }

}
