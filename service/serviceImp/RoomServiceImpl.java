package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.model.Room;
import com.example.hotelmanagementapi.model.payload.RoomDto;
import com.example.hotelmanagementapi.repository.RoomRepository;
import com.example.hotelmanagementapi.service.RoomService;
import com.example.hotelmanagementapi.util.RoomType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoomDto saveRoom(RoomDto roomDto) {
        Room room = modelMapper.map(roomDto, Room.class);
        room = roomRepository.save(room);
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto updateRoom(Long roomId, RoomDto roomDto) {
        Room existingRoom = roomRepository.findById(roomId).orElse(null);
        if (existingRoom != null) {
            modelMapper.map(roomDto, existingRoom);
            existingRoom = roomRepository.save(existingRoom);
            return modelMapper.map(existingRoom, RoomDto.class);
        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .map(room -> modelMapper.map(room, RoomDto.class))
                .orElse(null);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findAvailableRooms(Timestamp checkIn, Timestamp checkOut, RoomType roomType) {
        List<Room> availableRooms = roomRepository.findAvailableRooms(checkIn, checkOut, roomType);
        return availableRooms.stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }
}

