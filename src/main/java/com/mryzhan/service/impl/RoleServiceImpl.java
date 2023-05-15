package com.mryzhan.service.impl;

import com.mryzhan.dto.RoleDTO;
import com.mryzhan.mapper.MapperUtil;
import com.mryzhan.repository.RoleRepository;
import com.mryzhan.service.RoleService;
import com.mryzhan.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RoleDTO> findALlRoles() {
//        return roleRepository.findAll().stream().map(roleMapper::convertToDTO).collect(Collectors.toList());
          return roleRepository.findAll().stream().map(role -> mapperUtil.convert(role, new RoleDTO())).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return mapperUtil.convert(roleRepository.findById(id).get(), new RoleDTO());
//        return roleMapper.convertToDTO(roleRepository.findById(id).get());
    }
}
