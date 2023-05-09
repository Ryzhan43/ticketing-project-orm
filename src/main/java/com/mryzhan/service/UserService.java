package com.mryzhan.service;

import com.mryzhan.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUsername(String username);
    UserDTO findByUserName(String username);
    UserDTO update(UserDTO userDTO);
    UserDTO findById(String username);
    List<UserDTO> findAllUsers();
    List<UserDTO> findAllManagers();
    List<UserDTO> listAllByRole(String role);
    void deleteByUserName(String userDTO);
    void save(UserDTO userDTO);
    void delete(String username);
}
