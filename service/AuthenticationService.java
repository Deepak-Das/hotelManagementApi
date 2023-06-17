package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.exception.UserAlreadyExistException;
import com.example.hotelmanagementapi.exception.UserNotExistException;
import com.example.hotelmanagementapi.model.AuthRequest;
import com.example.hotelmanagementapi.model.UserEntity;
import com.example.hotelmanagementapi.model.payload.AuthResponse;
import com.example.hotelmanagementapi.model.payload.UserDto;
import com.example.hotelmanagementapi.repository.UserRepository;
import com.example.hotelmanagementapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(UserDto userDto) {

        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        UserEntity user;

        if (!exists) {
            user = UserEntity
                    .builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .build();
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }

        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();


    }


    public AuthResponse authentication(AuthRequest authRequest) {
        //Todo change this to user not found
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(UserNotExistException::new);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                        authRequest.getPassword())
        );
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
