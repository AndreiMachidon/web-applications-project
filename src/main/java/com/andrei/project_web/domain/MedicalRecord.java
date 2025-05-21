package com.andrei.project_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private String diagnosis;
    private LocalDateTime creationDate;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
}
