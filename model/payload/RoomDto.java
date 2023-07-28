package com.example.hotelmanagementapi.model.payload;

import com.example.hotelmanagementapi.util.Condition;
import com.example.hotelmanagementapi.util.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoomDto {
    private Long roomId;
    private String roomName;
    private String description;
    private Integer adultCount;
    private Integer childCount;
    private Set<Condition> conditions;
    private Set<RoomType> types;
    private Set<String> images;
    // Assuming you don't need the 'Reservation' property in the DTO
}
