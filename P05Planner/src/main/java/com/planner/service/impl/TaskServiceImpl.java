package com.planner.service.impl;

import com.planner.model.dtos.TaskAddDTO;
import com.planner.model.entity.Task;
import com.planner.model.entity.User;
import com.planner.model.enums.PriorityName;
import com.planner.repo.TaskRepository;
import com.planner.service.PriorityService;
import com.planner.service.TaskService;
import com.planner.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final PriorityService priorityService;

    public TaskServiceImpl(ModelMapper modelMapper, TaskRepository taskRepository, UserService userService, PriorityService priorityService) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.priorityService = priorityService;
    }

    @Override
    public void saveTask(TaskAddDTO taskAddDTO) {
        User user = userService.findCurrendUser();
        Task task = modelMapper.map(taskAddDTO,Task.class);
        task.setUser(user);
        task.setPriority(this.priorityService.findPriorityByName(taskAddDTO.getPriority()));
        this.taskRepository.saveAndFlush(task);
    }
}
