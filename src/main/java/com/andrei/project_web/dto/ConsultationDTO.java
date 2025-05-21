package com.andrei.project_web.dto;

import com.andrei.project_web.domain.Appointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationDTO {
    private long id;
    private String summary;
    private String prescription;
    private boolean followUpNeeded;
    private Appointment appointment;
}
