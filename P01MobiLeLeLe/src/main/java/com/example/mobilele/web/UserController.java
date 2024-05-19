package com.example.mobilele.web;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
        model.setViewName(isRegistered ? "redirect:/" : "/auth-register");
        return model;
    }

    @PostMapping("/login")
    public ModelAndView postLogin(ModelAndView model, UserDto userDto, HttpSession session){
        boolean isLogged = this.userService.checkUserLogin(userDto);
        model.setViewName(isLogged ? "redirect:/" : "/auth-login");
        return model;
    }
}
