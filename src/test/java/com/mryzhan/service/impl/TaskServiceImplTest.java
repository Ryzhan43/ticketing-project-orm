package com.mryzhan.service.impl;

import com.mryzhan.dto.TaskDTO;
import com.mryzhan.entity.Task;
import com.mryzhan.mapper.TaskMapper;
import com.mryzhan.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L,2L,3l, -1L})
    void findById_test(long id){

        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDTO(task)).thenReturn(new TaskDTO());

        taskService.findById(id);

        verify(taskRepository).findById(id);
        verify(taskMapper).convertToDTO(any(Task.class));

        verify(taskRepository, never()).findById(-5L);

    }

    @Test
    void findById_bdd_test(){

        Task task = new Task();

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDTO(task)).willReturn(new TaskDTO());

        taskService.findById(anyLong());

        then(taskRepository).should().findById(anyLong());
        then(taskRepository).should(never()).findById(-5L);
    }



}