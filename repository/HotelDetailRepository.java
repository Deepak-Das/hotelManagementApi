package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.HotelDetail;
import com.example.hotelmanagementapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelDetailRepository extends JpaRepository<HotelDetail,Long> {
}
