package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto saveReservation(ReservationDto reservationDto, Long userId, Long branchId);
    ReservationDto updateReservation(Long reservationId, ReservationDto reservationDto);
    ReservationDto getReservationById(Long reservationId);
    List<ReservationDto> getReservationsByFirstNameAndLastName(String firstName, String lastName);
    List<ReservationDto> getReservationsByRoomName(String roomName);
}
