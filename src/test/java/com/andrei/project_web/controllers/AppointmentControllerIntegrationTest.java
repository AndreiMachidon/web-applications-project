package com.andrei.project_web.controllers;

import com.andrei.project_web.domain.enums.Status;
import com.andrei.project_web.dto.AppointmentDTO;
import com.andrei.project_web.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class AppointmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService appointmentService;

    private final LocalDateTime startTime = LocalDateTime.of(2025, 7, 4, 10, 0);
    private final LocalDateTime endTime = LocalDateTime.of(2025, 7, 4, 11, 0);

    @Test
    public void testGetAllAppointments() throws Exception {
        AppointmentDTO dto = AppointmentDTO.builder()
                .status(Status.CONFIRMED)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        when(appointmentService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].status").value("CONFIRMED"));
    }

    @Test
    public void testGetAppointmentById() throws Exception {
        AppointmentDTO dto = AppointmentDTO.builder()
                .status(Status.CANCELLED)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        when(appointmentService.findById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELLED"));
    }

    @Test
    public void testCreateAppointment() throws Exception {
        AppointmentDTO input = AppointmentDTO.builder()
                .status(Status.PENDING)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        when(appointmentService.save(any())).thenReturn(input);

        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "PENDING",
                                "startTime": "2025-07-04T10:00:00",
                                "endTime": "2025-07-04T11:00:00"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testUpdateAppointment() throws Exception {
        AppointmentDTO updated = AppointmentDTO.builder()
                .status(Status.CONFIRMED)
                .startTime(startTime)
                .endTime(endTime)
                .build();

        when(appointmentService.save(any())).thenReturn(updated);

        mockMvc.perform(put("/api/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "CONFIRMED",
                                "startTime": "2025-07-04T10:00:00",
                                "endTime": "2025-07-04T11:00:00"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/api/appointments/1"))
                .andExpect(status().isNoContent());

        verify(appointmentService).deleteById(1L);
    }
}

