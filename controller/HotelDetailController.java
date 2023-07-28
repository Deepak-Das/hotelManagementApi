package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.payload.HotelDetailDto;
import com.example.hotelmanagementapi.service.HotelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/hotels")
public class HotelDetailController {
    private final HotelDetailService hotelDetailService;

    @Autowired
    public HotelDetailController(HotelDetailService hotelDetailService) {
        this.hotelDetailService = hotelDetailService;
    }

    @PostMapping
    public ResponseEntity<HotelDetailDto> createHotelDetail(@RequestBody HotelDetailDto hotelDetailDto) {
        HotelDetailDto createdHotelDetail = hotelDetailService.saveHotelDetail(hotelDetailDto);
        return new ResponseEntity<>(createdHotelDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDetailDto> updateHotelDetail(@PathVariable Long hotelId, @RequestBody HotelDetailDto hotelDetailDto) {
        HotelDetailDto updatedHotelDetail = hotelDetailService.updateHotelDetail(hotelId, hotelDetailDto);
        if (updatedHotelDetail != null) {
            return new ResponseEntity<>(updatedHotelDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Map<String ,String>> deleteHotelDetail(@PathVariable Long hotelId) {
        hotelDetailService.deleteHotelDetail(hotelId);
        Map<String ,String> apiRes= new HashMap<>();
        apiRes.put("status : ","Successfully");
        return new ResponseEntity<>(apiRes,HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDetailDto> getHotelDetailById(@PathVariable Long hotelId) {
        HotelDetailDto hotelDetailDto = hotelDetailService.getHotelDetailById(hotelId);
        if (hotelDetailDto != null) {
            return new ResponseEntity<>(hotelDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<HotelDetailDto>> getAllHotelDetails() {
        List<HotelDetailDto> hotelDetails = hotelDetailService.getAllHotelDetails();
        return new ResponseEntity<>(hotelDetails, HttpStatus.OK);
    }
}
