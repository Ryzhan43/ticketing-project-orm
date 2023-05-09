package com.mryzhan.service;

import com.mryzhan.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void deleteByUserName(String userDTO);
    UserDTO findById(String username);
    List<UserDTO> findAllManagers();

    UserDTO findByUsername(String username);
    void delete(String username);
}
