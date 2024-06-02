package com.dictionary.controller;


import com.dictionary.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private final WordService wordService;

    public HomeController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView model){
 ////1!!!!!
        model.setViewName("home");
        return model;
    }
}
