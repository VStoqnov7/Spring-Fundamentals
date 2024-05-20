package com.example.mobilele.web;

import com.example.mobilele.models.dto.UserDto;
import com.example.mobilele.models.dto.UserLoginDto;
import com.example.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ModelAndView postRegister(ModelAndView model, @Valid UserDto userDto, BindingResult bindingResult){
        boolean existUser = this.userService.existUser(userDto);

        if (bindingResult.hasFieldErrors("firstName")) {
            model.addObject("invalidFirstName", true);
        }
        if (bindingResult.hasFieldErrors("lastName")) {
            model.addObject("invalidLastName", true);
        }
        if (bindingResult.hasFieldErrors("username") || existUser) {
            model.addObject("invalidUserName", true);
        }
        if (bindingResult.hasFieldErrors("password")) {
            model.addObject("invalidPassword", true);
        }

        if (bindingResult.hasErrors() || existUser) {
            model.setViewName("/auth-register");
            return model;
        }
        userService.saveUser(userDto);
        model.setViewName("redirect:/");
        return model;
    }

    @PostMapping("/login")
    public ModelAndView postLogin(ModelAndView model, @Valid UserLoginDto userLoginDto, BindingResult bindingResult, HttpSession session){
        boolean isLogged = this.userService.checkUserLogin(userLoginDto);
        if (bindingResult.hasErrors() || !isLogged){
            model.addObject("invalidUsernameOrPassword", true);
            model.setViewName("/auth-login");
            return model;
        }
        session.setAttribute("user", userLoginDto);
        model.addObject("invalidUsernameOrPassword", false);
        model.setViewName("redirect:/");
        return model;
    }

    @PostMapping("/logout")
    public ModelAndView postLogout(ModelAndView model) {
        this.userService.logout();
        model.setViewName("redirect:/");
        return model;
    }
}
