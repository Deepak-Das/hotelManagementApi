package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.payload.BranchDto;
import com.example.hotelmanagementapi.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {
    private final BranchService branchDetailService;

    @Autowired
    public BranchController(BranchService branchDetailService) {
        this.branchDetailService = branchDetailService;
    }

    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<BranchDto> createBranchDetail(@RequestBody BranchDto branchDetailDto , @PathVariable(value = "hotelId") Long hotelId) {
        BranchDto createdBranchDetail = branchDetailService.saveBranchDetail(branchDetailDto,hotelId);
        return new ResponseEntity<>(createdBranchDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDto> updateBranchDetail(@PathVariable Long branchId, @RequestBody BranchDto branchDetailDto) {
        BranchDto updatedBranchDetail = branchDetailService.updateBranchDetail(branchId, branchDetailDto);
        if (updatedBranchDetail != null) {
            return new ResponseEntity<>(updatedBranchDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<Void> deleteBranchDetail(@PathVariable Long branchId) {
        branchDetailService.deleteBranchDetail(branchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDto> getBranchDetailById(@PathVariable Long branchId) {
        BranchDto branchDetailDto = branchDetailService.getBranchDetailById(branchId);
        if (branchDetailDto != null) {
            return new ResponseEntity<>(branchDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranchDetails() {
        List<BranchDto> branchDetails = branchDetailService.getAllBranchDetails();
        return new ResponseEntity<>(branchDetails, HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<BranchDto>> getBranchDetailByHotelId(@PathVariable Long hotelId) {
        List<BranchDto> branchDetails = branchDetailService.getBranchDetailByHotelId(hotelId);
        return new ResponseEntity<>(branchDetails, HttpStatus.OK);
    }
}

