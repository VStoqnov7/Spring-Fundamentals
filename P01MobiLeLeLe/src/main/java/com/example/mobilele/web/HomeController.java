package com.example.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView getHome(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("auth-login");
        return model;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.setViewName("auth-register");
        return model;
    }

    @GetMapping("/offers/add")
    public ModelAndView offersAdd(ModelAndView model){
        model.setViewName("offer-add");
        return model;
    }

    @GetMapping("/brands/all")
    public ModelAndView allBrands(ModelAndView model){
        model.setViewName("brands");
        return model;
    }

    @GetMapping("/offers/all")
    public ModelAndView allOffer(ModelAndView model){
        model.setViewName("offers");
        return model;
    }
}
