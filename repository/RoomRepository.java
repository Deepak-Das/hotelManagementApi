package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.Room;
import com.example.hotelmanagementapi.util.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("SELECT DISTINCT r FROM Room r LEFT JOIN r.reservations res " +
            "WHERE (res.checkIn >= :checkOut OR res.checkOut <= :checkIn AND res.active = false ) " +
            "AND (:roomType IS NULL OR :roomType MEMBER OF r.types)")
    List<Room> findAvailableRooms(
            @Param("checkIn") Timestamp checkIn,
            @Param("checkOut") Timestamp checkOut,
            @Param("roomType") RoomType roomType
    );

}
