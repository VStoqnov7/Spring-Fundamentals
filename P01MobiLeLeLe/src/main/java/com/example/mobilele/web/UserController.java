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

    @PostMapping("/register")
    public ModelAndView postRegister(ModelAndView model, UserDto userDto){
        boolean isRegistered = userService.saveUser(userDto);
        if (isRegistered) {
            model.setViewName("redirect:/");
        }else {
            model.setViewName("/auth-register");
        }
        return model;
    }

//    @PostMapping("/login")
//    public ModelAndView postLogin(ModelAndView model, UserDto userDto){
//        this.userService.checkUser(userDto);
////        if ()
//
//    }
}
