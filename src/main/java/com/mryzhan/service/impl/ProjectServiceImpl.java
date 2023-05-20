package com.mryzhan.service.impl;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.dto.TaskDTO;
import com.mryzhan.dto.UserDTO;
import com.mryzhan.entity.Project;
import com.mryzhan.entity.User;
import com.mryzhan.enums.Status;
import com.mryzhan.mapper.ProjectMapper;
import com.mryzhan.mapper.UserMapper;
import com.mryzhan.repository.ProjectRepository;
import com.mryzhan.repository.TaskRepository;
import com.mryzhan.service.ProjectService;
import com.mryzhan.service.TaskService;
import com.mryzhan.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;


    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository, @Lazy UserService userService, UserMapper userMapper, TaskService taskService) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);

        Project project =projectMapper.convertToEntity(projectDTO);
        projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO dto) {
        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(dto);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());

        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
        Project project =  projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode() + " - " + project.getId());
        projectRepository.save(project);
        taskService.deleteByProject(projectMapper.convertToDTO(project));
//        taskService.listAllTasks().stream()
//                .filter(p->p.getProject().equals(findByProjectCode(code)))
//                .map(TaskDTO::getId)
//                .forEach(taskService::delete);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);


        taskService.completeByProject(projectMapper.convertToDTO(project));
    }

    @Override
    public List<ProjectDTO> listAllProjectsDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDTO currentUserDTO = userService.findByUserName(username);
        User user = userMapper.convertToEntity(currentUserDTO);

        //List<Project> list = projectRepository.findAll();
        List<Project> list = projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO obj = projectMapper.convertToDTO(project);

            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));
            //obj.setCompleteTaskCounts(1);
            return obj;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(User assignedManager) {
        List<Project> list = projectRepository.findAllByAssignedManager(assignedManager);
        return list.stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public ProjectDTO findByProjectCode(String source) {
        Project project = projectRepository.findByProjectCode(source);
        return projectMapper.convertToDTO(project);
    }

}
