package com.mryzhan.service;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.entity.Project;
import com.mryzhan.entity.User;

import javax.persistence.PreUpdate;
import java.util.List;

public interface ProjectService {
    ProjectDTO findByProjectCode(String source);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO projectDTO);
    void update(ProjectDTO dto);
    void delete(String code);

    void complete(ProjectDTO projectCode);

    List<ProjectDTO> listAllProjectsDetails();

    List<ProjectDTO> readAllByAssignedManager(User assignedManager);
}
