package com.example.hotelmanagementapi.model.payload;

import com.example.hotelmanagementapi.util.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class ReservationDto {
    private Long reservationId;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private ReservationStatus status;
    private String mode;
    private Boolean active;
    private Long userId;
    private Long branchId;
    private Set<Long> roomIds;
}

