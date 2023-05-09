package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectRepository.save(projectMapper.convertToEntity(projectDTO));
    }


    @Override
    public ProjectDTO findById(Long source) {
        return projectMapper.convertToDTO(projectRepository.findById(source).get());
    }
}
