package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.service.ConsultationService;
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
public class ConsultationControllerTest {

    @Mock
    private ConsultationService consultationService;

    @InjectMocks
    private ConsultationController consultationController;

    @Test
    public void testGetAllConsultations() {
        List<ConsultationDTO> consultations = List.of(new ConsultationDTO(), new ConsultationDTO());
        when(consultationService.findAll()).thenReturn(consultations);

        ResponseEntity<List<ConsultationDTO>> response = consultationController.getAllConsultations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetConsultationById() {
        Long id = 1L;
        ConsultationDTO dto = new ConsultationDTO();
        dto.setId(id);
        when(consultationService.findById(id)).thenReturn(dto);

        ResponseEntity<ConsultationDTO> response = consultationController.getConsultationById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateConsultation() {
        ConsultationDTO dto = new ConsultationDTO();
        dto.setSummary("Test Consultation");

        when(consultationService.save(dto)).thenReturn(dto);

        ResponseEntity<ConsultationDTO> response = consultationController.createConsultation(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Consultation", response.getBody().getSummary());
    }

    @Test
    public void testUpdateConsultation() {
        Long id = 1L;
        ConsultationDTO dto = new ConsultationDTO();
        dto.setSummary("Updated Reason");

        when(consultationService.save(dto)).thenReturn(dto);

        ResponseEntity<ConsultationDTO> response = consultationController.updateConsultation(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Reason", response.getBody().getSummary());
    }

    @Test
    public void testDeleteConsultation() {
        Long id = 1L;

        ResponseEntity<Void> response = consultationController.deleteConsultation(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(consultationService).deleteById(id);
    }
}

