package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.RoomDto;
import com.example.hotelmanagementapi.util.RoomType;

import java.sql.Timestamp;
import java.util.List;

public interface RoomService {
    RoomDto saveRoom(RoomDto roomDto);
    RoomDto updateRoom(Long roomId, RoomDto roomDto);
    void deleteRoom(Long roomId);
    RoomDto getRoomById(Long roomId);
    List<RoomDto> getAllRooms();
    List<RoomDto> findAvailableRooms(Timestamp checkIn, Timestamp checkOut, RoomType roomType);
}
