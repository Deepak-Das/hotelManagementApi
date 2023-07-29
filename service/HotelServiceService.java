package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.HotelServiceDTO;

import java.util.List;

public interface HotelServiceService {
    HotelServiceDTO createService(HotelServiceDTO serviceDTO);
    HotelServiceDTO getServiceById(Long serviceId);
    List<HotelServiceDTO> getAllServices();
    List<HotelServiceDTO> getServicesByBranchId(Long branchId);
    HotelServiceDTO updateService(Long serviceId, HotelServiceDTO serviceDTO);
    void deleteService(Long serviceId);
}
