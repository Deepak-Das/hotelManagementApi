package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.exception.ResourceNotFoundException;
import com.example.hotelmanagementapi.model.HotelService;
import com.example.hotelmanagementapi.model.payload.HotelServiceDTO;
import com.example.hotelmanagementapi.repository.ServiceRepository;
import com.example.hotelmanagementapi.service.HotelServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceServiceImpl implements HotelServiceService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HotelServiceDTO createService(HotelServiceDTO serviceDTO) {
        HotelService service = modelMapper.map(serviceDTO, HotelService.class);
        service = serviceRepository.save(service);
        return modelMapper.map(service, HotelServiceDTO.class);
    }

    @Override
    public HotelServiceDTO getServiceById(Long serviceId) {
        HotelService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service","service Id",serviceId));
        return modelMapper.map(service, HotelServiceDTO.class);
    }

    @Override
    public List<HotelServiceDTO> getAllServices() {
        List<HotelService> services = serviceRepository.findAll();
        return services.stream()
                .map(service -> modelMapper.map(service, HotelServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelServiceDTO> getServicesByBranchId(Long branchId) {
        List<HotelService> services = serviceRepository.findByBranchBranchId(branchId);
        return services.stream()
                .map(service -> modelMapper.map(service, HotelServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HotelServiceDTO updateService(Long serviceId, HotelServiceDTO serviceDTO) {
        HotelService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service","service Id",serviceId));

        modelMapper.map(serviceDTO, service);
        service = serviceRepository.save(service);
        return modelMapper.map(service, HotelServiceDTO.class);
    }

    @Override
    public void deleteService(Long serviceId) {
        HotelService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service","service Id",serviceId));

        serviceRepository.delete(service);
    }
}
