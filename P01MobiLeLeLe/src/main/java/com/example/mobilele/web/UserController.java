package com.example.mobilele.web;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public ModelAndView postRegister(ModelAndView model, UserDto userDto){
        userService.saveUser(userDto);
        model.setViewName("redirect:/");
        return model;
    }
}
