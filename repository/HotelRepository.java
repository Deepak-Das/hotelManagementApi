package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
