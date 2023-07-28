package com.example.hotelmanagementapi.service.serviceImp;


import com.example.hotelmanagementapi.exception.ResourceNotFoundException;
import com.example.hotelmanagementapi.model.HotelDetail;
import com.example.hotelmanagementapi.model.payload.HotelDetailDto;
import com.example.hotelmanagementapi.repository.HotelDetailRepository;
import com.example.hotelmanagementapi.service.HotelDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelDetailServiceImpl implements HotelDetailService {
    private final HotelDetailRepository hotelDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelDetailServiceImpl(HotelDetailRepository hotelDetailRepository, ModelMapper modelMapper) {
        this.hotelDetailRepository = hotelDetailRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public HotelDetailDto saveHotelDetail(HotelDetailDto hotelDetailDto) {
        HotelDetail hotelDetail = modelMapper.map(hotelDetailDto,HotelDetail.class);
        hotelDetail = hotelDetailRepository.save(hotelDetail);
        return modelMapper.map(hotelDetail,HotelDetailDto.class);
    }

    @Override
    public HotelDetailDto updateHotelDetail(Long hotelId, HotelDetailDto hotelDetailDto) {
        HotelDetail existingHotelDetail = hotelDetailRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel information","hotel id",hotelDetailDto.getHotelId()));
        if (existingHotelDetail != null) {
            existingHotelDetail.setName(hotelDetailDto.getName());
            existingHotelDetail.setDescription(hotelDetailDto.getDescription());
            existingHotelDetail.setEmail(hotelDetailDto.getEmail());
            existingHotelDetail.setContact(hotelDetailDto.getContact());
            existingHotelDetail.setImages(hotelDetailDto.getImages());
            existingHotelDetail = hotelDetailRepository.save(existingHotelDetail);
            return modelMapper.map(existingHotelDetail,HotelDetailDto.class);
        }
        return null;
    }

    @Override
    public void deleteHotelDetail(Long hotelId) {
        hotelDetailRepository.deleteById(hotelId);
    }

    @Override
    public HotelDetailDto getHotelDetailById(Long hotelId) {
        return hotelDetailRepository.findById(hotelId)
                .map(hotelDetail -> modelMapper.map(hotelDetail,HotelDetailDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("hotel information","hotel id",hotelId));
    }

    @Override
    public List<HotelDetailDto> getAllHotelDetails() {
        List<HotelDetail> hotelDetails = hotelDetailRepository.findAll();
        return hotelDetails.stream()
                .map(hotelDetail -> modelMapper.map(hotelDetail,HotelDetailDto.class))
                .collect(Collectors.toList());
    }

    // Helper method to convert from Entity to DTO
/*
    private HotelDetailDto convertToDto(HotelDetail hotelDetail) {
        HotelDetailDto dto = new HotelDetailDto();
        dto.setHotelId(hotelDetail.getHotelId());
        dto.setName(hotelDetail.getName());
        dto.setDescription(hotelDetail.getDescription());
        dto.setEmail(hotelDetail.getEmail());
        dto.setContact(hotelDetail.getContact());
        dto.setImages(hotelDetail.getImages());
        return dto;
    }
*/

}