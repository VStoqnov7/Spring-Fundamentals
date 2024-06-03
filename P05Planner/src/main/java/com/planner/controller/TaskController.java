package com.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class TaskController {

    @GetMapping("/task")
    public ModelAndView addTask(ModelAndView model){
        model.setViewName("task-add");
        return model;
    }
}
