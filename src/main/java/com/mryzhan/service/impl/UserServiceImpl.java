package com.mryzhan.service.impl;

import com.mryzhan.dto.UserDTO;
import com.mryzhan.entity.User;
import com.mryzhan.mapper.UserMapper;
import com.mryzhan.repository.UserRepository;
import com.mryzhan.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll(Sort.by("firstName"))
                .stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        User convertedUser = userMapper.convertToEntity(userDTO);
        convertedUser.setId(user.getId());
        userRepository.save(convertedUser);

        return findByUsername(userDTO.getUserName());
    }

    @Override
    public void deleteByUserName(String userDTO) {
        userRepository.deleteByUserName(userDTO);
    }

    @Override
    public UserDTO findById(String username) {
        return userMapper.convertToDTO(userRepository.findByUserName(username));
    }

    @Override
    public List<UserDTO> findAllManagers() {
        return userRepository.findAllByRole_DescriptionIsManager().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.convertToDTO(userRepository.findByUserName(username));
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);
    }
}
