package com.mryzhan.service.impl;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Task;
import com.mryzhan.entity.User;
import com.mryzhan.enums.Status;
import com.mryzhan.mapper.ProjectMapper;
import com.mryzhan.mapper.TaskMapper;
import com.mryzhan.repository.TaskRepository;
import com.mryzhan.repository.UserRepository;
import com.mryzhan.service.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;
    ProjectMapper projectMapper;
    UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
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
            convertedTask.setTaskStatus(dto.getTaskStatus() == null ? foundTask.get().getTaskStatus() : dto.getTaskStatus());
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

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
        List<TaskDTO> list = listAllByProject(projectDTO);
        list.stream().map(TaskDTO::getId).forEach(this::delete);
    }

    @Override
    public void completeByProject(ProjectDTO projectDTO) {

        List<TaskDTO> list = listAllByProject(projectDTO);
        list.stream().forEach(taskDTO -> {
                taskDTO.setTaskStatus(Status.COMPLETE);
                update(taskDTO);
        });
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status complete) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User loggedInUser = userRepository.findByUserName(username);
        List<Task> list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(complete, loggedInUser);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO dto) {
        Optional<Task> task = taskRepository.findById(dto.getId());

        if(task.isPresent())
        {
            task.get().setTaskStatus(dto.getTaskStatus());
            taskRepository.save(task.get());
        }
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status complete) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User loggedInUser = userRepository.findByUserName(username);
        List<Task> list = taskRepository.findAllByTaskStatusIsAndAssignedEmployee(complete, loggedInUser);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> readAllByAssignedEmployee(User user) {
        List<Task> list = taskRepository.findAllByAssignedEmployee(user);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }


    private List<TaskDTO> listAllByProject(ProjectDTO projectDTO) {
        List<Task> list = taskRepository.findAllByProject(projectMapper.convertToEntity(projectDTO));
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }
}
