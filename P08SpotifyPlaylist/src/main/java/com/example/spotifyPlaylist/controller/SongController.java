package com.example.spotifyPlaylist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class SongController {


    @GetMapping()
    public ModelAndView home(ModelAndView model){
        model.setViewName("home");
        return model;
    }
}
