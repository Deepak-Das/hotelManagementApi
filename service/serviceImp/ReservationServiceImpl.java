package com.example.hotelmanagementapi.service.serviceImp;

import com.example.hotelmanagementapi.model.Branch;
import com.example.hotelmanagementapi.model.Reservation;
import com.example.hotelmanagementapi.model.User;
import com.example.hotelmanagementapi.model.payload.ReservationDto;
import com.example.hotelmanagementapi.repository.BranchRepository;
import com.example.hotelmanagementapi.repository.ReservationRepository;
import com.example.hotelmanagementapi.repository.UserRepository;
import com.example.hotelmanagementapi.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository,
                                  BranchRepository branchRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReservationDto saveReservation(ReservationDto reservationDto, Long userId, Long branchId) {
        User user = userRepository.findById(userId).orElse(null);
        Branch branch = branchRepository.findById(branchId).orElse(null);

        if (user == null || branch == null) {
            return null;
        }

        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservation.setUser(user);
        reservation.setBranchDetail(branch);
        reservation = reservationRepository.save(reservation);

        return modelMapper.map(reservation, ReservationDto.class);
    }


    @Override
    public ReservationDto updateReservation(Long reservationId, ReservationDto reservationDto) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElse(null);
        if (existingReservation == null) {
            return null;
        }

        modelMapper.map(reservationDto, existingReservation);
        existingReservation = reservationRepository.save(existingReservation);

        return modelMapper.map(existingReservation, ReservationDto.class);
    }

    @Override
    public ReservationDto getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return (reservation != null) ? modelMapper.map(reservation, ReservationDto.class) : null;
    }

    @Override
    public List<ReservationDto> getReservationsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            List<Reservation> reservations = reservationRepository.findByUser(user);
            return reservations.stream()
                    .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ReservationDto> getReservationsByRoomName(String roomName) {
        List<Reservation> reservations = reservationRepository.findByRoomsRoomName(roomName);
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }
}
