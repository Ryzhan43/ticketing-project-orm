package com.mryzhan.service.impl;

import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Task;
import com.mryzhan.enums.Status;
import com.mryzhan.mapper.TaskMapper;
import com.mryzhan.repository.TaskRepository;
import com.mryzhan.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO findByID(Long id) {
        return taskMapper.convertToDTO(taskRepository.findById(id).get());
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(dto);
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO dto) {
        Optional<Task> foundTask = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);

        if (foundTask.isPresent()){
            convertedTask.setId(foundTask.get().getId());
            convertedTask.setTaskStatus(foundTask.get().getTaskStatus());
            convertedTask.setAssignedDate(foundTask.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }
    }

    @Override
    public TaskDTO findById(Long taskId) {

            Optional<Task> task = taskRepository.findById(taskId);
            if(task.isPresent()){
                return taskMapper.convertToDTO(task.get());
            }
            return null;
        }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }
}
