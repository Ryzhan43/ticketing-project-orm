package com.mryzhan.service;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.entity.Project;

import javax.persistence.PreUpdate;
import java.util.List;

public interface ProjectService {
    ProjectDTO findById(Long source);
    ProjectDTO findByProjectCode(String source);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO projectDTO);
    void update(ProjectDTO dto);
    void delete(String code);

    void deleteById(String projectcode);

    void complete(ProjectDTO projectCode);
}
