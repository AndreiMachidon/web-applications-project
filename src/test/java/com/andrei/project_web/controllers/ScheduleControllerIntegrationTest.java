package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ScheduleDTO;
import com.andrei.project_web.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
public class ScheduleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScheduleService scheduleService;

    private final Long fixedId = 1L;

    @Test
    public void testGetAllSchedules() throws Exception {
        ScheduleDTO dto = ScheduleDTO.builder().
                id(fixedId)
                .build();
        when(scheduleService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(fixedId));
    }

    @Test
    public void testGetScheduleById() throws Exception {
        ScheduleDTO dto = ScheduleDTO
                .builder()
                .id(fixedId)
                .build();
        when(scheduleService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/schedules/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(fixedId));
    }

    @Test
    public void testCreateSchedule() throws Exception {
        ScheduleDTO dto = ScheduleDTO.builder()
                .id(fixedId)
                .build();
        when(scheduleService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(fixedId));
    }

    @Test
    public void testUpdateSchedule() throws Exception {
        ScheduleDTO dto = ScheduleDTO.builder()
                .id(fixedId)
                .build();
        when(scheduleService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/schedules/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(fixedId));
    }

    @Test
    public void testDeleteSchedule() throws Exception {
        mockMvc.perform(delete("/api/schedules/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(scheduleService).deleteById(fixedId);
    }
}

