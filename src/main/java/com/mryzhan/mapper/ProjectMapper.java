package com.mryzhan.mapper;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convertToEntity
    public Project convertToEntity(ProjectDTO dto){
        return modelMapper.map(dto, Project.class);
    }

    //convertToDto
    public ProjectDTO convertToDTO(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
}
