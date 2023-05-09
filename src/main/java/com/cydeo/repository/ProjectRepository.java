package com.cydeo.repository;

import com.cydeo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectBy(Long id);


}
