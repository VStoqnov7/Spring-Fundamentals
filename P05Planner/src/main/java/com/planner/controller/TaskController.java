package com.planner.controller;

import com.planner.model.dtos.TaskAddDTO;
import com.planner.model.entity.Task;
import com.planner.model.enums.PriorityName;
import com.planner.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @ModelAttribute(name = "taskAddDTO")
    public TaskAddDTO taskAddForm(){
        return new TaskAddDTO();
    }

    @GetMapping("/addTask")
    public ModelAndView addTask(ModelAndView model){
        model.addObject("prioritiesNameValues", PriorityName.values());
        model.setViewName("task-add");
        return model;
    }

    @PostMapping("/addTask")
    public ModelAndView register(ModelAndView model,
                                 @Valid TaskAddDTO taskAddDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("prioritiesNameValues", PriorityName.values());
            model.setViewName("task-add");
            return model;
        }
        this.taskService.saveTask(taskAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }
}
