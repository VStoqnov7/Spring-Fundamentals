package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RegisterController {


    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.setViewName("register");
        return model;
    }
}
