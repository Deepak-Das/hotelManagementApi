package com.example.hotelmanagementapi.model;

import com.example.hotelmanagementapi.util.HotelType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelBranchDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long branchId;


    private String name;
    private String description;
    private String email;
    @Enumerated(value = EnumType.ORDINAL)
    @ElementCollection
    private Set<HotelType> type;
    private String address;
    @Column(nullable = false)
    private Timestamp checkIn;
    @Column(nullable = false)
    private Timestamp checkOut;
    private Long contact;

    @ElementCollection
    private Set<String> images;

    //Todo:mapping with hotel branch m-o
}
