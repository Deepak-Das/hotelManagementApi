package com.example.hotelmanagementapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingServiceId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private HotelService service;

    @Column(nullable = false)
    private int count;



}
