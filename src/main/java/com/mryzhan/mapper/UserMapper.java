package com.mryzhan.mapper;

import com.mryzhan.dto.UserDTO;
import com.mryzhan.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO convertToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
