package com.example.hotelmanagementapi.model.payload;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetailDto {

        private Long hotelId;
        private String name;
        private String description;
        private String email;
        private Long contact;
        private Set<String> images;

        // Constructors, getters, and setters
}
