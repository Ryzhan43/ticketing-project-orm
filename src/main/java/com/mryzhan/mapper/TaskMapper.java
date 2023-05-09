package com.mryzhan.mapper;

import com.mryzhan.dto.RoleDTO;
import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Role;
import com.mryzhan.entity.Task;
import org.modelmapper.ModelMapper;

public class TaskMapper{
    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convertToEntity
    public Task convertToEntity(TaskDTO dto){
        return modelMapper.map(dto, Task.class);
    }


    //convertToDto
    public TaskDTO convertToDTO(Task task){
        return modelMapper.map(task, TaskDTO.class);
    }
}
