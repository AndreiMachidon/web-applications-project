package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.domain.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicDTO {
    private long id;
    private String name;
    private String location;
    private String phoneNumber;
    private List<Room> rooms;
    private List<Doctor> doctors;
}
