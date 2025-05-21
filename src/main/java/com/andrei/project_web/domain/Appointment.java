package com.andrei.project_web.domain;

import com.andrei.project_web.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Consultation consultation;
}
