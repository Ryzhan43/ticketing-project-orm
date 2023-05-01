package com.cydeo.repository;

import com.cydeo.entity.Role;
import com.cydeo.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findAllByRoleId(Long id);
}
