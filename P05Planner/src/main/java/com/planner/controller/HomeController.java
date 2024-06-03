package com.planner.controller;

import com.planner.model.entity.Task;
import com.planner.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/home")
    public ModelAndView home(ModelAndView model){
        List<Task> allAssignedTasks = this.taskService.findAllAssignedTasks();
        List<Task> allTasks = this.taskService.findAllTasks();
        model.addObject("allAssignedTasks",allAssignedTasks);
        model.addObject("allTasks",allTasks);
        model.setViewName("home");
        return model;
    }

}
