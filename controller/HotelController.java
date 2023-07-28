package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.payload.HotelDto;
import com.example.hotelmanagementapi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private final HotelService hotelDetailService;

    @Autowired
    public HotelController(HotelService hotelDetailService) {
        this.hotelDetailService = hotelDetailService;
    }

    @PostMapping
    public ResponseEntity<HotelDto> createHotelDetail(@RequestBody HotelDto hotelDetailDto) {
        HotelDto createdHotelDetail = hotelDetailService.saveHotelDetail(hotelDetailDto);
        return new ResponseEntity<>(createdHotelDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelDetail(@PathVariable Long hotelId, @RequestBody HotelDto hotelDetailDto) {
        HotelDto updatedHotelDetail = hotelDetailService.updateHotelDetail(hotelId, hotelDetailDto);
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
    public ResponseEntity<HotelDto> getHotelDetailById(@PathVariable Long hotelId) {
        HotelDto hotelDetailDto = hotelDetailService.getHotelDetailById(hotelId);
        if (hotelDetailDto != null) {
            return new ResponseEntity<>(hotelDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotelDetails() {
        List<HotelDto> hotelDetails = hotelDetailService.getAllHotelDetails();
        return new ResponseEntity<>(hotelDetails, HttpStatus.OK);
    }
}
