package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
}
