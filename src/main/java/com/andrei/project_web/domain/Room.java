package com.andrei.project_web.domain;

import com.andrei.project_web.domain.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long roomNumber;
    private RoomType type;
    private int capacity;

    @ManyToOne
    private Clinic clinic;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;
}
