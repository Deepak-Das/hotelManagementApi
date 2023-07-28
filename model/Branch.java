package com.example.hotelmanagementapi.model;

import com.example.hotelmanagementapi.util.HotelType;
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
public class Branch {
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




//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_detail_id")
    private Hotel hotelDetail;

    @OneToMany(mappedBy = "branchDetail", orphanRemoval = true)
    private Set<Reservation> reservations = new LinkedHashSet<>();

}
