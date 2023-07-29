package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.payload.HotelServiceDTO;
import com.example.hotelmanagementapi.service.HotelServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    private final HotelServiceService serviceService;

    @Autowired
    public ServiceController(HotelServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<HotelServiceDTO> createService(@RequestBody HotelServiceDTO serviceDTO) {
        HotelServiceDTO createdService = serviceService.createService(serviceDTO);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<HotelServiceDTO> getServiceById(@PathVariable Long serviceId) {
        HotelServiceDTO service = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }

    @GetMapping
    public ResponseEntity<List<HotelServiceDTO>> getAllServices() {
        List<HotelServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<HotelServiceDTO>> getServicesByBranchId(@PathVariable Long branchId) {
        List<HotelServiceDTO> services = serviceService.getServicesByBranchId(branchId);
        return ResponseEntity.ok(services);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<HotelServiceDTO> updateService(@PathVariable Long serviceId, @RequestBody HotelServiceDTO serviceDTO) {
        HotelServiceDTO updatedService = serviceService.updateService(serviceId, serviceDTO);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) {
        serviceService.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
