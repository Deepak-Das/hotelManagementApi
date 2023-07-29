package com.example.hotelmanagementapi.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private  String name;
    private  String description;
    private  Integer rate;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;



}
