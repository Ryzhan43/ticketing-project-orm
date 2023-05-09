package com.mryzhan.service;

import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Task;

import java.util.List;

public interface TaskService {
    TaskDTO findByID(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

    TaskDTO findById(Long taskId);
}
