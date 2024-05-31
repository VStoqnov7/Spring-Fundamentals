package com.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WordController {

    @GetMapping("/wordAdd")
    public ModelAndView register(ModelAndView model){
        model.setViewName("word-add");
        return model;
    }
}
