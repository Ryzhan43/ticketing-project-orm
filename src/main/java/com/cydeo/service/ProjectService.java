package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    void save(ProjectDTO projectDTO);

    ProjectDTO findById(Long source);
}
