package com.example.hotelmanagementapi.model;

import com.example.hotelmanagementapi.util.Condition;
import com.example.hotelmanagementapi.util.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    //Todo:brach Id m-o (hotel)


    @Column(nullable = false)
    private String roomName;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Integer adultCount;

    @Column(nullable = true)
    private Integer childCount;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Condition.class)
    private Set<Condition> conditions;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = RoomType.class)
    private Set<RoomType> types;

    @ElementCollection
    private Set<String> images;
}
