package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.domain.Room;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleDTO {
    private long id;
    private LocalDateTime scheduleDateTime;
    private Room room;
    private Doctor doctor;
}
