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
public class Reservation {

    //Reservation mapping done

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;


    @Column(nullable = false)
    private Timestamp checkIn;
    @Column(nullable = false)
    private Timestamp checkOut;
    @Column(nullable = false,length = 40)
    private String status;
    @Column(nullable = false,length = 40)
    private String mode;

    private Boolean active;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "branch_id")
    private BranchDetail branchDetail;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "Reservation_rooms",
            joinColumns = @JoinColumn(name = "reservationId"),
            inverseJoinColumns = @JoinColumn(name = "roomId"))
    private Set<Room> rooms = new LinkedHashSet<>();

}
