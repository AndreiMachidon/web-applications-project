package com.andrei.project_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String summary;
    private String prescription;
    private boolean followUpNeeded;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Appointment appointment;
}
