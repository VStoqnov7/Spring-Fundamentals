package com.paintingscollectors.controller;

import com.paintingscollectors.model.dto.UserRegistrationDTO;
import com.paintingscollectors.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "userRegistrationDTO")
    public UserRegistrationDTO userRegistrationForm(){
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.setViewName("register");
        return model;
    }


    @PostMapping("/register")
    public ModelAndView register(ModelAndView model,
                                 @Valid UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.setViewName("register");
            return model;
        }

        this.userService.saveUser(userRegistrationDTO);

        model.setViewName("redirect:/");
        return model;
    }
}
