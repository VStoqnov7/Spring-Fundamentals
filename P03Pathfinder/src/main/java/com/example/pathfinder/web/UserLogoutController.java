package com.example.pathfinder.web;

import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserLogoutController {
    private final UserService userService;

    public UserLogoutController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView model){
        this.userService.logout();
        model.setViewName("redirect:/");
        return model;
    }
}
