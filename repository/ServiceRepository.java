package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<HotelService, Long> {
    List<HotelService> findByBranchBranchId(Long branchId);

}
