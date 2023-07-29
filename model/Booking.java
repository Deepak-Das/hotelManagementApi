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

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "booking_id")
    private Set<Service> services = new LinkedHashSet<>();

    //todo: o-o reservation

}
