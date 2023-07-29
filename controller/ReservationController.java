package com.example.hotelmanagementapi.controller;

import com.example.hotelmanagementapi.model.payload.ReservationDto;
import com.example.hotelmanagementapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/save/{userId}/{branchId}")
    public ResponseEntity<ReservationDto> saveReservation(
            @RequestBody ReservationDto reservationDto,
            @PathVariable Long userId,
            @PathVariable Long branchId
    ) {
        ReservationDto savedReservation = reservationService.saveReservation(reservationDto, userId, branchId);
        if (savedReservation != null) {
            return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Other endpoints...

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody ReservationDto reservationDto
    ) {
        ReservationDto updatedReservation = reservationService.updateReservation(reservationId, reservationDto);
        if (updatedReservation != null) {
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long reservationId) {
        ReservationDto reservationDto = reservationService.getReservationById(reservationId);
        if (reservationDto != null) {
            return new ResponseEntity<>(reservationDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username")
    public ResponseEntity<List<ReservationDto>> getReservationsByUsername(@RequestParam("firstName") String firstName,
                                                                          @RequestParam("lastName") String lastName) {
        List<ReservationDto> reservations = reservationService.getReservationsByFirstNameAndLastName(firstName,  lastName);
        if (!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/room/{roomName}")
    public ResponseEntity<List<ReservationDto>> getReservationsByRoomName(@PathVariable String roomName) {
        List<ReservationDto> reservations = reservationService.getReservationsByRoomName(roomName);
        if (!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
