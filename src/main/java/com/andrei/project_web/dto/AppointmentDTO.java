package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private Patient patient;
    private Doctor doctor;
    private Consultation consultation;
}
