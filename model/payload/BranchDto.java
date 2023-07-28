package com.example.hotelmanagementapi.model.payload;

import com.example.hotelmanagementapi.util.HotelType;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private Long branchId;
    private String name;
    private String description;
    private String email;
    private Set<HotelType> type;
    private String address;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Long contact;
//    private HotelDto hotelDto;
    private Set<String> images;
}
