package com.example.hotelmanagementapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    private Long hostRoomId;//Todo:m-o booking;

    //Todo: o-o room
    //Todo: o-o booking
    //Todo: o-m userId
    //Todo: o-o adharcard no.

}
