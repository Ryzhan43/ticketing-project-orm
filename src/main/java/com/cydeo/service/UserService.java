package com.cydeo.service;

import com.cydeo.controller.UserController;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void deleteByUserName(UserDTO userDTO);

}
