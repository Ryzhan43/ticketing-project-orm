package com.mryzhan.service.impl;

import com.mryzhan.dto.ProjectDTO;
import com.mryzhan.entity.Project;
import com.mryzhan.entity.Role;
import com.mryzhan.entity.User;
import com.mryzhan.enums.Gender;
import com.mryzhan.enums.Status;
import com.mryzhan.mapper.ProjectMapper;
import com.mryzhan.mapper.UserMapper;
import com.mryzhan.repository.ProjectRepository;
import com.mryzhan.service.TaskService;
import com.mryzhan.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ProjectServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;
    @InjectMocks
    ProjectServiceImpl projectService;

    /**
     * Method under test: {@link ProjectServiceImpl#listAllProjects()}
     */
    @Test
    void testListAllProjects() {
        when(projectRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(projectServiceImpl.listAllProjects().isEmpty());
        verify(projectRepository).findAll();
    }

    @Test
    void getByProjectCode_test() {

        //Given
        Project project = new Project();
        ProjectDTO projectDTO = new ProjectDTO();

        when(projectRepository.findByProjectCode("")).thenReturn(project);
        when(projectMapper.convertToDTO(project)).thenReturn(projectDTO);

        //When
        ProjectDTO projectDTO1 = projectService.findByProjectCode(anyString());

        //Then
        verify(projectRepository).findByProjectCode(anyString());
        verify(projectMapper).convertToDTO(any(Project.class));

        assertNotNull(projectDTO1);

    }

    @Test
    void getByProjectCode_exception_test() {
        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found"));

        Throwable exception = assertThrows(RuntimeException.class, () -> projectService.findByProjectCode(""));

        verify(projectRepository).findByProjectCode(anyString());

        assertEquals(exception.getMessage(), "Project Not Found");
    }

    @Test
    void save_test() {
        ProjectDTO projectDTO = new ProjectDTO();
        Project project = new Project();

        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);

        projectService.save(projectDTO);

        verify(projectRepository).save(project);
    }

    /**
     * Method under test: {@link ProjectServiceImpl#save(ProjectDTO)}
     */
    @Test
    void testSave() {
        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(assignedManager);
        project.setEndDate(LocalDate.of(1970, 1, 1));
        project.setId(1L);
        project.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setInsertUserId(1L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setLastUpdateUserId(1L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectMapper.convertToEntity(Mockito.<ProjectDTO>any())).thenReturn(project);

        Role role2 = new Role();
        role2.setDescription("The characteristics of someone or something");
        role2.setId(1L);
        role2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setInsertUserId(1L);
        role2.setIsDeleted(true);
        role2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setLastUpdateUserId(1L);

        User assignedManager2 = new User();
        assignedManager2.setEnabled(true);
        assignedManager2.setFirstName("Jane");
        assignedManager2.setGender(Gender.MALE);
        assignedManager2.setId(1L);
        assignedManager2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setInsertUserId(1L);
        assignedManager2.setIsDeleted(true);
        assignedManager2.setLastName("Doe");
        assignedManager2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setLastUpdateUserId(1L);
        assignedManager2.setPassWord("Pass Word");
        assignedManager2.setPhone("6625550144");
        assignedManager2.setRole(role2);
        assignedManager2.setUserName("janedoe");

        Project project2 = new Project();
        project2.setAssignedManager(assignedManager2);
        project2.setEndDate(LocalDate.of(1970, 1, 1));
        project2.setId(1L);
        project2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setInsertUserId(1L);
        project2.setIsDeleted(true);
        project2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setLastUpdateUserId(1L);
        project2.setProjectCode("Project Code");
        project2.setProjectDetail("Project Detail");
        project2.setProjectName("Project Name");
        project2.setProjectStatus(Status.OPEN);
        project2.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectRepository.save(Mockito.<Project>any())).thenReturn(project2);
        ProjectDTO projectDTO = new ProjectDTO();
        projectServiceImpl.save(projectDTO);
        verify(projectMapper).convertToEntity(Mockito.<ProjectDTO>any());
        verify(projectRepository).save(Mockito.<Project>any());
        assertEquals(Status.OPEN, projectDTO.getProjectStatus());
    }

    /**
     * Method under test: {@link ProjectServiceImpl#update(ProjectDTO)}
     */
    @Test
    void testUpdate() {
        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(assignedManager);
        project.setEndDate(LocalDate.of(1970, 1, 1));
        project.setId(1L);
        project.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setInsertUserId(1L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setLastUpdateUserId(1L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectMapper.convertToEntity(Mockito.<ProjectDTO>any())).thenReturn(project);

        Role role2 = new Role();
        role2.setDescription("The characteristics of someone or something");
        role2.setId(1L);
        role2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setInsertUserId(1L);
        role2.setIsDeleted(true);
        role2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setLastUpdateUserId(1L);

        User assignedManager2 = new User();
        assignedManager2.setEnabled(true);
        assignedManager2.setFirstName("Jane");
        assignedManager2.setGender(Gender.MALE);
        assignedManager2.setId(1L);
        assignedManager2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setInsertUserId(1L);
        assignedManager2.setIsDeleted(true);
        assignedManager2.setLastName("Doe");
        assignedManager2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setLastUpdateUserId(1L);
        assignedManager2.setPassWord("Pass Word");
        assignedManager2.setPhone("6625550144");
        assignedManager2.setRole(role2);
        assignedManager2.setUserName("janedoe");

        Project project2 = new Project();
        project2.setAssignedManager(assignedManager2);
        project2.setEndDate(LocalDate.of(1970, 1, 1));
        project2.setId(1L);
        project2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setInsertUserId(1L);
        project2.setIsDeleted(true);
        project2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setLastUpdateUserId(1L);
        project2.setProjectCode("Project Code");
        project2.setProjectDetail("Project Detail");
        project2.setProjectName("Project Name");
        project2.setProjectStatus(Status.OPEN);
        project2.setStartDate(LocalDate.of(1970, 1, 1));

        Role role3 = new Role();
        role3.setDescription("The characteristics of someone or something");
        role3.setId(1L);
        role3.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role3.setInsertUserId(1L);
        role3.setIsDeleted(true);
        role3.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role3.setLastUpdateUserId(1L);

        User assignedManager3 = new User();
        assignedManager3.setEnabled(true);
        assignedManager3.setFirstName("Jane");
        assignedManager3.setGender(Gender.MALE);
        assignedManager3.setId(1L);
        assignedManager3.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager3.setInsertUserId(1L);
        assignedManager3.setIsDeleted(true);
        assignedManager3.setLastName("Doe");
        assignedManager3.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager3.setLastUpdateUserId(1L);
        assignedManager3.setPassWord("Pass Word");
        assignedManager3.setPhone("6625550144");
        assignedManager3.setRole(role3);
        assignedManager3.setUserName("janedoe");

        Project project3 = new Project();
        project3.setAssignedManager(assignedManager3);
        project3.setEndDate(LocalDate.of(1970, 1, 1));
        project3.setId(1L);
        project3.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project3.setInsertUserId(1L);
        project3.setIsDeleted(true);
        project3.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project3.setLastUpdateUserId(1L);
        project3.setProjectCode("Project Code");
        project3.setProjectDetail("Project Detail");
        project3.setProjectName("Project Name");
        project3.setProjectStatus(Status.OPEN);
        project3.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectRepository.save(Mockito.<Project>any())).thenReturn(project3);
        when(projectRepository.findByProjectCode(Mockito.<String>any())).thenReturn(project2);
        projectServiceImpl.update(new ProjectDTO());
        verify(projectMapper).convertToEntity(Mockito.<ProjectDTO>any());
        verify(projectRepository).findByProjectCode(Mockito.<String>any());
        verify(projectRepository).save(Mockito.<Project>any());
    }

    /**
     * Method under test: {@link ProjectServiceImpl#delete(String)}
     */
    @Test
    void testDelete() {
        when(projectMapper.convertToDTO(Mockito.<Project>any())).thenReturn(new ProjectDTO());

        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(assignedManager);
        project.setEndDate(LocalDate.of(1970, 1, 1));
        project.setId(1L);
        project.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setInsertUserId(1L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setLastUpdateUserId(1L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.of(1970, 1, 1));

        Role role2 = new Role();
        role2.setDescription("The characteristics of someone or something");
        role2.setId(1L);
        role2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setInsertUserId(1L);
        role2.setIsDeleted(true);
        role2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setLastUpdateUserId(1L);

        User assignedManager2 = new User();
        assignedManager2.setEnabled(true);
        assignedManager2.setFirstName("Jane");
        assignedManager2.setGender(Gender.MALE);
        assignedManager2.setId(1L);
        assignedManager2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setInsertUserId(1L);
        assignedManager2.setIsDeleted(true);
        assignedManager2.setLastName("Doe");
        assignedManager2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setLastUpdateUserId(1L);
        assignedManager2.setPassWord("Pass Word");
        assignedManager2.setPhone("6625550144");
        assignedManager2.setRole(role2);
        assignedManager2.setUserName("janedoe");

        Project project2 = new Project();
        project2.setAssignedManager(assignedManager2);
        project2.setEndDate(LocalDate.of(1970, 1, 1));
        project2.setId(1L);
        project2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setInsertUserId(1L);
        project2.setIsDeleted(true);
        project2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setLastUpdateUserId(1L);
        project2.setProjectCode("Project Code");
        project2.setProjectDetail("Project Detail");
        project2.setProjectName("Project Name");
        project2.setProjectStatus(Status.OPEN);
        project2.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectRepository.save(Mockito.<Project>any())).thenReturn(project2);
        when(projectRepository.findByProjectCode(Mockito.<String>any())).thenReturn(project);
        doNothing().when(taskService).deleteByProject(Mockito.<ProjectDTO>any());
        projectServiceImpl.delete("Code");
        verify(projectMapper).convertToDTO(Mockito.<Project>any());
        verify(projectRepository).findByProjectCode(Mockito.<String>any());
        verify(projectRepository).save(Mockito.<Project>any());
        verify(taskService).deleteByProject(Mockito.<ProjectDTO>any());
    }

    /**
     * Method under test: {@link ProjectServiceImpl#complete(ProjectDTO)}
     */
    @Test
    void testComplete() {
        when(projectMapper.convertToDTO(Mockito.<Project>any())).thenReturn(new ProjectDTO());

        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(assignedManager);
        project.setEndDate(LocalDate.of(1970, 1, 1));
        project.setId(1L);
        project.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setInsertUserId(1L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setLastUpdateUserId(1L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.of(1970, 1, 1));

        Role role2 = new Role();
        role2.setDescription("The characteristics of someone or something");
        role2.setId(1L);
        role2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setInsertUserId(1L);
        role2.setIsDeleted(true);
        role2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role2.setLastUpdateUserId(1L);

        User assignedManager2 = new User();
        assignedManager2.setEnabled(true);
        assignedManager2.setFirstName("Jane");
        assignedManager2.setGender(Gender.MALE);
        assignedManager2.setId(1L);
        assignedManager2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setInsertUserId(1L);
        assignedManager2.setIsDeleted(true);
        assignedManager2.setLastName("Doe");
        assignedManager2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager2.setLastUpdateUserId(1L);
        assignedManager2.setPassWord("Pass Word");
        assignedManager2.setPhone("6625550144");
        assignedManager2.setRole(role2);
        assignedManager2.setUserName("janedoe");

        Project project2 = new Project();
        project2.setAssignedManager(assignedManager2);
        project2.setEndDate(LocalDate.of(1970, 1, 1));
        project2.setId(1L);
        project2.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setInsertUserId(1L);
        project2.setIsDeleted(true);
        project2.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project2.setLastUpdateUserId(1L);
        project2.setProjectCode("Project Code");
        project2.setProjectDetail("Project Detail");
        project2.setProjectName("Project Name");
        project2.setProjectStatus(Status.OPEN);
        project2.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectRepository.save(Mockito.<Project>any())).thenReturn(project2);
        when(projectRepository.findByProjectCode(Mockito.<String>any())).thenReturn(project);
        doNothing().when(taskService).completeByProject(Mockito.<ProjectDTO>any());
        projectServiceImpl.complete(new ProjectDTO());
        verify(projectMapper).convertToDTO(Mockito.<Project>any());
        verify(projectRepository).findByProjectCode(Mockito.<String>any());
        verify(projectRepository).save(Mockito.<Project>any());
        verify(taskService).completeByProject(Mockito.<ProjectDTO>any());
    }

    /**
     * Method under test: {@link ProjectServiceImpl#listAllProjectsDetails()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testListAllProjectsDetails() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at com.mryzhan.service.impl.ProjectServiceImpl.listAllProjectsDetails(ProjectServiceImpl.java:90)
        //   See https://diff.blue/R013 to resolve this issue.

        projectServiceImpl.listAllProjectsDetails();
    }

    /**
     * Method under test: {@link ProjectServiceImpl#readAllByAssignedManager(User)}
     */
    @Test
    void testReadAllByAssignedManager() {
        when(projectRepository.findAllByAssignedManager(Mockito.<User>any())).thenReturn(new ArrayList<>());

        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");
        assertTrue(projectServiceImpl.readAllByAssignedManager(assignedManager).isEmpty());
        verify(projectRepository).findAllByAssignedManager(Mockito.<User>any());
    }

    /**
     * Method under test: {@link ProjectServiceImpl#findByProjectCode(String)}
     */
    @Test
    void testFindByProjectCode() {
        ProjectDTO projectDTO = new ProjectDTO();
        when(projectMapper.convertToDTO(Mockito.<Project>any())).thenReturn(projectDTO);

        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(1L);
        role.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setInsertUserId(1L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        role.setLastUpdateUserId(1L);

        User assignedManager = new User();
        assignedManager.setEnabled(true);
        assignedManager.setFirstName("Jane");
        assignedManager.setGender(Gender.MALE);
        assignedManager.setId(1L);
        assignedManager.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setInsertUserId(1L);
        assignedManager.setIsDeleted(true);
        assignedManager.setLastName("Doe");
        assignedManager.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assignedManager.setLastUpdateUserId(1L);
        assignedManager.setPassWord("Pass Word");
        assignedManager.setPhone("6625550144");
        assignedManager.setRole(role);
        assignedManager.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(assignedManager);
        project.setEndDate(LocalDate.of(1970, 1, 1));
        project.setId(1L);
        project.setInsertDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setInsertUserId(1L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        project.setLastUpdateUserId(1L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.of(1970, 1, 1));
        when(projectRepository.findByProjectCode(Mockito.<String>any())).thenReturn(project);
        assertSame(projectDTO, projectServiceImpl.findByProjectCode("Source"));
        verify(projectMapper).convertToDTO(Mockito.<Project>any());
        verify(projectRepository).findByProjectCode(Mockito.<String>any());
    }

}