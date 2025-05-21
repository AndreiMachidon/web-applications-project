package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.domain.enums.RoomType;
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
public class RoomDTO {
    private long id;
    private long roomNumber;
    private RoomType type;
    private int capacity;
    private Clinic clinic;
    private List<Schedule> schedules;
}
