package com.example.pathfinder.web;

import com.example.pathfinder.models.User;
import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{username}")
    public ModelAndView profile(@PathVariable("username") String username, ModelAndView model){
        User user = this.userService.findByUsername(username);
        model.addObject("user",user);
        model.setViewName("profile");
        return model;
    }



}
