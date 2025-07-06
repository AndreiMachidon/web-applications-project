package com.andrei.project_web.controllers;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.domain.enums.Status;
import com.andrei.project_web.dto.AppointmentDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    public void testGetAllAppointments() {
        List<AppointmentDTO> list = List.of(new AppointmentDTO(), new AppointmentDTO());

        when(appointmentService.findAll()).thenReturn(list);

        ResponseEntity<List<AppointmentDTO>> response = appointmentController.getAllAppointments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(appointmentService).findAll();
    }

    @Test
    public void testGetAppointmentById() {
        long id = 1L;
        AppointmentDTO dto = AppointmentDTO.builder()
                .status(Status.PENDING)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1))
                .build();

        when(appointmentService.findById(id)).thenReturn(dto);

        ResponseEntity<AppointmentDTO> response = appointmentController.getAppointmentById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Status.PENDING, response.getBody().getStatus());
        verify(appointmentService).findById(id);
    }

    @Test
    public void testCreateAppointment() {
        AppointmentDTO input = AppointmentDTO.builder()
                .status(Status.PENDING)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1))
                .build();

        AppointmentDTO saved = AppointmentDTO.builder()
                .status(Status.PENDING)
                .startTime(input.getStartTime())
                .endTime(input.getEndTime())
                .build();

        when(appointmentService.save(input)).thenReturn(saved);

        ResponseEntity<AppointmentDTO> response = appointmentController.createAppointment(input);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(Status.PENDING, response.getBody().getStatus());
        verify(appointmentService).save(input);
    }

    @Test
    public void testUpdateAppointment() {
        Long id = 1L;
        AppointmentDTO input = AppointmentDTO.builder()
                .status(Status.CANCELLED)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(30))
                .build();

        AppointmentDTO updated = AppointmentDTO.builder()
                .status(Status.CANCELLED)
                .startTime(input.getStartTime())
                .endTime(input.getEndTime())
                .build();

        when(appointmentService.save(input)).thenReturn(updated);

        ResponseEntity<AppointmentDTO> response = appointmentController.updateAppointment(id, input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Status.CANCELLED, response.getBody().getStatus());
        verify(appointmentService).save(input);
    }

    @Test
    public void testDeleteAppointment() {
        Long id = 1L;

        ResponseEntity<Void> response = appointmentController.deleteAppointment(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(appointmentService).deleteById(id);
    }

    @Test
    public void testHandleNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Appointment not found");

        ResponseEntity<String> response = appointmentController.handleNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Appointment not found", response.getBody());
    }
}
