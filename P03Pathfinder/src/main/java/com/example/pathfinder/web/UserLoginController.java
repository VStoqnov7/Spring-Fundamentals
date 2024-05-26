package com.example.pathfinder.web;


import com.example.pathfinder.models.dto.UserLoginDTO;
import com.example.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute(name = "userLoginDTO")
    public UserLoginDTO userLoginFormDto() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("login");
        return model;
    }
    @PostMapping("/login")
    public ModelAndView login(ModelAndView model, @Valid UserLoginDTO userLoginDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.setViewName("login");
            model.addObject("invalidLogin",true);
            model.addObject("userRegistrationDTO", userLoginDTO);
            return model;
        }
        this.userService.login(userLoginDTO);

        model.setViewName("redirect:/");
        return model;
    }


}
