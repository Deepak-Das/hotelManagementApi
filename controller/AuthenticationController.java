package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.AuthRequest;
import com.example.hotelmanagementapi.model.payload.AuthResponse;
import com.example.hotelmanagementapi.model.payload.UserDto;
import com.example.hotelmanagementapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register( @RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.register(userDto));

    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> register( @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authenticationService.authentication(authRequest));

    }
}
