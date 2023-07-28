package com.example.hotelmanagementapi.repository;

import com.example.hotelmanagementapi.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    // Add custom queries here if needed
    List<Branch> findByHotelDetail_HotelId(Long hotelId);


}
