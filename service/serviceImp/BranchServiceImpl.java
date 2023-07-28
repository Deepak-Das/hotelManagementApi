package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.exception.ResourceNotFoundException;
import com.example.hotelmanagementapi.model.Branch;
import com.example.hotelmanagementapi.model.Hotel;
import com.example.hotelmanagementapi.model.payload.BranchDto;
import com.example.hotelmanagementapi.repository.BranchRepository;
import com.example.hotelmanagementapi.repository.HotelRepository;
import com.example.hotelmanagementapi.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchDetailRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchDetailRepository, HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.branchDetailRepository = branchDetailRepository;
        this.hotelRepository=hotelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BranchDto saveBranchDetail(BranchDto branchDetailDto, Long hotelId) {
        Hotel existingHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotle","hotel Id",hotelId));

        Branch branchDetail = modelMapper.map(branchDetailDto, Branch.class);
        branchDetail = branchDetailRepository.save(branchDetail);
        branchDetail.setHotelDetail(existingHotel);
        return modelMapper.map(branchDetail, BranchDto.class);
    }

    @Override
    public BranchDto updateBranchDetail(Long branchId, BranchDto branchDetailDto) {
        Branch existingBranchDetail = branchDetailRepository.findById(branchId).orElseThrow(() -> new ResourceNotFoundException("Branch","Branch Id",branchId));;
        if (existingBranchDetail != null) {
            Branch updateBranchDetail = modelMapper.map(branchDetailDto, Branch.class);
            updateBranchDetail.setBranchId(branchId);
            existingBranchDetail = branchDetailRepository.save(updateBranchDetail);
            return modelMapper.map(updateBranchDetail, BranchDto.class);
        }
        return null;
    }

    @Override
    public void deleteBranchDetail(Long branchId) {
        branchDetailRepository.deleteById(branchId);
    }

    @Override
    public BranchDto getBranchDetailById(Long branchId) {
        return branchDetailRepository.findById(branchId)
                .map(branchDetail -> modelMapper.map(branchDetail, BranchDto.class))
                .orElse(null);
    }

    @Override
    public List<BranchDto> getAllBranchDetails() {
        List<Branch> branchDetails = branchDetailRepository.findAll();
        return branchDetails.stream()
                .map(branchDetail -> modelMapper.map(branchDetail, BranchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BranchDto> getBranchDetailByHotelId(Long hotelId) {
        return branchDetailRepository.findByHotelDetail_HotelId(hotelId).stream()
                .map(branchDetail -> modelMapper.map(branchDetail, BranchDto.class))
                .collect(Collectors.toList());
    }
}

