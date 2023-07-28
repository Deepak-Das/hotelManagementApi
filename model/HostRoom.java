package com.example.hotelmanagementapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HostRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hostRoomId;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "room_id")
    private Room room;



    //Todo: o-o adharcard no.
    private String adharcardNo;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
