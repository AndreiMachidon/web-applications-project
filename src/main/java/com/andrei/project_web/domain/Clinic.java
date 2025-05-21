package com.andrei.project_web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String location;
    private String phoneNumber;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    @JsonIgnore
    @ManyToMany(mappedBy = "clinics")
    private List<Doctor> doctors;
}
