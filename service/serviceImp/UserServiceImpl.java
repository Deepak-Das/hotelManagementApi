package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.model.User;
import com.example.hotelmanagementapi.model.payload.UserDto;
import com.example.hotelmanagementapi.repository.UserRepository;
import com.example.hotelmanagementapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            modelMapper.map(userDto, existingUser);
            existingUser = userRepository.save(existingUser);
            return modelMapper.map(existingUser, UserDto.class);
        }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}

