package com.mryzhan.service;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Task;
import com.mryzhan.entity.User;
import com.mryzhan.enums.Status;

import java.util.List;

public interface TaskService {
    TaskDTO findByID(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);
    TaskDTO findById(Long taskId);
    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);
    void deleteByProject(ProjectDTO convertToDTO);
    void completeByProject(ProjectDTO convertToDTO);
    List<TaskDTO> listAllTasksByStatusIsNot(Status complete);
    void updateStatus(TaskDTO task);
    List<TaskDTO> listAllTasksByStatus(Status complete);

    List<TaskDTO> readAllByAssignedEmployee(User user);
}
