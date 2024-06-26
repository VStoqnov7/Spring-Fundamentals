package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.UserRegistrationDTO;
import com.example.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserRegistrationController {

    private final UserService userService;
    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "userRegistrationDTO")
    public UserRegistrationDTO userLoginFormDto() {
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
                                 BindingResult bindingResult) {
        boolean checkUserName = this.userService.checkUserName(userRegistrationDTO.getUsername());
        boolean existUserEmail = this.userService.existUserByEmail(userRegistrationDTO.getEmail());

        if (bindingResult.hasFieldErrors("username") || checkUserName) {
            model.addObject("invalidUsername", true);
        }

        if (bindingResult.hasFieldErrors("fullName")) {
            model.addObject("invalidFullName", true);
        }

        if (bindingResult.hasFieldErrors("email") || existUserEmail) {
            model.addObject("invalidEmail", true);
        }

        if (bindingResult.hasFieldErrors("age")) {
            model.addObject("invalidAge", true);
        }

        if (bindingResult.hasFieldErrors("password") ) {
            model.addObject("invalidPassword", true);
        }

        if (bindingResult.hasFieldErrors("confirmPassword")) {
            model.addObject("invalidConfirmPassword", true);
        }

        if (bindingResult.hasErrors() || existUserEmail || checkUserName) {
            model.setViewName("register");
            model.addObject("userRegistrationDTO", userRegistrationDTO);
            return model;
        }

        userService.saveUser(userRegistrationDTO);

        model.setViewName("redirect:/");

        return model;
    }

}
