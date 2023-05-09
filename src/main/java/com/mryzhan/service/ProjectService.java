package com.mryzhan.service;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    void save(ProjectDTO projectDTO);

    ProjectDTO findById(Long source);
//
//    ProjectDTO findById(Long source);
}
