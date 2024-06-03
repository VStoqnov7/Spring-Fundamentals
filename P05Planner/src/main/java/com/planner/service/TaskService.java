package com.planner.service;

import com.planner.model.dtos.TaskAddDTO;

public interface TaskService {
    void saveTask(TaskAddDTO taskAddDTO);
}
