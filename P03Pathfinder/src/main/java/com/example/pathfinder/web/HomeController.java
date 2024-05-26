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



}
