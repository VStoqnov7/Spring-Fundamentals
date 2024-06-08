package com.likeBook.controller;

import com.likeBook.model.dtos.PostAddDTO;
import com.likeBook.model.entity.Post;
import com.likeBook.model.enums.MoodName;
import com.likeBook.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Post> allPosts = this.postService.findAllPosts();
        List<Post> myPosts = this.postService.findAllMyPosts();
        model.addObject("allPosts",allPosts);
        model.addObject("myPosts",myPosts);
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
    public ModelAndView addPost(ModelAndView model,
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

    @GetMapping("/removePost/{postId}")
    public ModelAndView removePost(@PathVariable String postId, ModelAndView model){
        this.postService.removePost(postId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/likePost/{postId}")
    public ModelAndView likePost(@PathVariable String postId, ModelAndView model){
        this.postService.likePost(postId);
        model.setViewName("redirect:/home");
        return model;
    }
}
