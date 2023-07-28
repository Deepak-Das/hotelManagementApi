package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.BranchDto;

import java.util.List;

public interface BranchService {
    BranchDto saveBranchDetail(BranchDto branchDetailDto, Long hotelId);
    BranchDto updateBranchDetail(Long branchId, BranchDto branchDetailDto);
    void deleteBranchDetail(Long branchId);
    BranchDto getBranchDetailById(Long branchId);
    List<BranchDto> getAllBranchDetails();

    List<BranchDto> getBranchDetailByHotelId(Long hotelId);

}
