package com.example.hotelmanagementapi.service.serviceImp;


import com.example.hotelmanagementapi.exception.ResourceNotFoundException;
import com.example.hotelmanagementapi.model.Hotel;
import com.example.hotelmanagementapi.model.payload.HotelDto;
import com.example.hotelmanagementapi.repository.HotelRepository;
import com.example.hotelmanagementapi.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelDetailRepository, ModelMapper modelMapper) {
        this.hotelDetailRepository = hotelDetailRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public HotelDto saveHotelDetail(HotelDto hotelDetailDto) {
        Hotel hotelDetail = modelMapper.map(hotelDetailDto, Hotel.class);
        hotelDetail = hotelDetailRepository.save(hotelDetail);
        return modelMapper.map(hotelDetail, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelDetail(Long hotelId, HotelDto hotelDetailDto) {
        Hotel existingHotelDetail = hotelDetailRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel information","hotel id",hotelDetailDto.getHotelId()));
        if (existingHotelDetail != null) {
            existingHotelDetail.setName(hotelDetailDto.getName());
            existingHotelDetail.setDescription(hotelDetailDto.getDescription());
            existingHotelDetail.setEmail(hotelDetailDto.getEmail());
            existingHotelDetail.setContact(hotelDetailDto.getContact());
            existingHotelDetail.setImages(hotelDetailDto.getImages());
            existingHotelDetail = hotelDetailRepository.save(existingHotelDetail);
            return modelMapper.map(existingHotelDetail, HotelDto.class);
        }
        return null;
    }

    @Override
    public void deleteHotelDetail(Long hotelId) {
        hotelDetailRepository.deleteById(hotelId);
    }

    @Override
    public HotelDto getHotelDetailById(Long hotelId) {
        return hotelDetailRepository.findById(hotelId)
                .map(hotelDetail -> modelMapper.map(hotelDetail, HotelDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("hotel information","hotel id",hotelId));
    }

    @Override
    public List<HotelDto> getAllHotelDetails() {
        List<Hotel> hotelDetails = hotelDetailRepository.findAll();
        return hotelDetails.stream()
                .map(hotelDetail -> modelMapper.map(hotelDetail, HotelDto.class))
                .collect(Collectors.toList());
    }


}