package com.example.hotelmanagementapi.service;

import com.example.hotelmanagementapi.model.payload.HotelDetailDto;

import java.util.List;

public interface HotelDetailService {
    HotelDetailDto saveHotelDetail(HotelDetailDto hotelDetail);
    HotelDetailDto updateHotelDetail(Long hotelId, HotelDetailDto hotelDetail);
    void deleteHotelDetail(Long hotelId);
    HotelDetailDto getHotelDetailById(Long hotelId);
    List<HotelDetailDto> getAllHotelDetails();
}
