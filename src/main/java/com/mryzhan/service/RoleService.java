package com.mryzhan.service;

import com.mryzhan.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findALlRoles();
    RoleDTO findById(Long id);
}
