package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.model.AuthRequest;
import com.example.hotelmanagementapi.model.payload.AuthResponse;
import com.example.hotelmanagementapi.model.payload.UserDto;

public interface AuthenticationService {
    public AuthResponse register(UserDto userDto) ;
    public AuthResponse authentication(AuthRequest authRequest);
}
