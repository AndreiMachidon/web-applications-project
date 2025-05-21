package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.domain.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {
    private long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private List<Appointment> appointments;
    private List<MedicalRecord> medicalRecords;
    private User user;
}
