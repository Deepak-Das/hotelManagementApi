package com.example.hotelmanagementapi.model.payload;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    String token;
}
