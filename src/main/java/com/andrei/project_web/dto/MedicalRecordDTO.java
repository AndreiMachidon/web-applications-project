package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.domain.Patient;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordDTO {
    private long id;
    private String description;
    private String diagnosis;
    private LocalDateTime creationDate;
    private Patient patient;
    private Doctor doctor;
}
