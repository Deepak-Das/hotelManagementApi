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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    //Todo:o-o with userId
    //Todo:o-o with branchId
    //Todo:o-m with roomId
    //Todo:o-o with paymentId

    @Column(nullable = false)
    private Timestamp checkIn;
    @Column(nullable = false)
    private Timestamp checkOut;
    @Column(nullable = false,length = 40)
    private String status;
    @Column(nullable = false,length = 40)
    private String mode;

    private Boolean active;

}
