package com.example.hotelmanagementapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;//Todo:m-o booking;

    //Todo: o-o room
    //Todo: o-m hosted room
    //Todo: o-o payment room
    //Todo: o-o reservation
    //Todo: o-m services

    @Column(nullable = false)
    private Timestamp checkIn;
    @Column(nullable = false)
    private Timestamp checkOut;

    @Column(nullable = false)
    private Boolean status;



}
