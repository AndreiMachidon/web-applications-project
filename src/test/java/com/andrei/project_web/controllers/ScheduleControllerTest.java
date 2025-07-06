package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ScheduleDTO;
import com.andrei.project_web.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    @Test
    public void testGetAllSchedules() {
        List<ScheduleDTO> schedules = List.of(new ScheduleDTO(), new ScheduleDTO());
        when(scheduleService.findAll()).thenReturn(schedules);

        ResponseEntity<List<ScheduleDTO>> response = scheduleController.getAllSchedules();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetScheduleById() {
        Long id = 1L;
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(id);
        when(scheduleService.findById(id)).thenReturn(dto);

        ResponseEntity<ScheduleDTO> response = scheduleController.getScheduleById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateSchedule() {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(1L);

        when(scheduleService.save(dto)).thenReturn(dto);

        ResponseEntity<ScheduleDTO> response = scheduleController.createSchedule(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testUpdateSchedule() {
        Long id = 1L;
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(1L);

        when(scheduleService.save(dto)).thenReturn(dto);

        ResponseEntity<ScheduleDTO> response = scheduleController.updateSchedule(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testDeleteSchedule() {
        Long id = 1L;

        ResponseEntity<Void> response = scheduleController.deleteSchedule(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(scheduleService).deleteById(id);
    }
}

