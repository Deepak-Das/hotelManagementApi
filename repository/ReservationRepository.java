package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.Reservation;
import com.example.hotelmanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByRoomsRoomName(String roomName);}

