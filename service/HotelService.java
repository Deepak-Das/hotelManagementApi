package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto saveHotelDetail(HotelDto hotelDetail);
    HotelDto updateHotelDetail(Long hotelId, HotelDto hotelDetail);
    void deleteHotelDetail(Long hotelId);
    HotelDto getHotelDetailById(Long hotelId);
    List<HotelDto> getAllHotelDetails();
}
