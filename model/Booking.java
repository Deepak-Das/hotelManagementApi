package com.example.hotelmanagementapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

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


//    @Column(nullable = false)
//    private Timestamp checkIn;
//    @Column(nullable = false)
//    private Timestamp checkOut;

    @Column(nullable = false)
    private Boolean status;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "booking")
    private Set<BookingService> bookingServices = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
