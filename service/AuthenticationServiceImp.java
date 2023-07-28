package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.exception.UserAlreadyExistException;
import com.example.hotelmanagementapi.exception.UserNotExistException;
import com.example.hotelmanagementapi.model.AuthRequest;
import com.example.hotelmanagementapi.model.User;
import com.example.hotelmanagementapi.model.payload.AuthResponse;
import com.example.hotelmanagementapi.model.payload.UserDto;
import com.example.hotelmanagementapi.repository.UserRepository;
import com.example.hotelmanagementapi.security.JwtService;
import com.example.hotelmanagementapi.service.serviceImp.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(UserDto userDto) {

        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        User user;

        if (!exists) {
            user = User
                    .builder()
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .adharcard(null)
                    .hQualification(null)
//                    .doj(LocalDateTime.now())
                    .doj(Timestamp.from(Instant.now()))
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
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(UserNotExistException::new);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                        authRequest.getPassword())
        );
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
