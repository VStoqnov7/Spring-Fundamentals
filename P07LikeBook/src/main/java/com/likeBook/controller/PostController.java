package com.likeBook.controller;

import com.likeBook.model.dtos.PostAddDTO;
import com.likeBook.model.enums.MoodName;
import com.likeBook.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @ModelAttribute(name = "postAddDTO")
    public PostAddDTO postAddDTOForm(){
        return new PostAddDTO();
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        model.setViewName("home");
        return model;
    }


    @GetMapping("/addPost")
    public ModelAndView addPost(ModelAndView model){
        model.addObject("moodNameValues", MoodName.values());
        model.setViewName("post-add");
        return model;
    }

    @PostMapping("/addPost")
    public ModelAndView addTask(ModelAndView model,
                                @Valid PostAddDTO postAddDTO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("moodNameValues", MoodName.values());
            model.setViewName("post-add");
            return model;
        }

        this.postService.savePost(postAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }

}
