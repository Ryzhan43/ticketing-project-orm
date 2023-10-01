package com.mryzhan.service.impl;

import com.mryzhan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    void deleteByUsername_test(){
        userService.deleteByUserName("mikesmith@cydeo.com");

        Mockito.verify(userService).deleteByUserName("mikesmith@cydeo.com");
//        Mockito.verify(userService, times(2)).deleteByUserName("mikesmith@cydeo.com");
//        Mockito.verify(userService, atLeastOnce()).deleteByUserName("mikesmith@cydeo.com");
//        Mockito.verify(userService, atLeast(2)).deleteByUserName("mikesmith@cydeo.com");
//        Mockito.verify(userService, atMost(2)).deleteByUserName("mikesmith@cydeo.com");
        Mockito.verify(userService, atMostOnce()).deleteByUserName("mikesmith@cydeo.com");

        InOrder inOrder = inOrder(userRepository);

        inOrder.verify(userRepository).findAll();

    }

}