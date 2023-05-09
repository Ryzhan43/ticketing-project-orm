package com.mryzhan.mapper;

import com.mryzhan.dto.RoleDTO;
import com.mryzhan.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convertToEntity
    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }


    //convertToDto
    public RoleDTO convertToDTO(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }
}
