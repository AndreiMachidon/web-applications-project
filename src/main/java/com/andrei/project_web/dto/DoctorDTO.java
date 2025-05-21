package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.domain.enums.Role;
import com.andrei.project_web.domain.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorDTO {
    private long id;
    private String name;
    private String email;
    private String specialization;
    private String licenseNumber;
    private Role role;
    private List<Clinic> clinics;
    private List<Appointment> appointments;
    private List<MedicalRecord> medicalRecords;
    private List<Schedule> schedules;
    private User user;
}
