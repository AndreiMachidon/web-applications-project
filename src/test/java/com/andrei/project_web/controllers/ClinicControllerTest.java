package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.service.ClinicService;
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
public class ClinicControllerTest {

    @Mock
    private ClinicService clinicService;

    @InjectMocks
    private ClinicController clinicController;

    @Test
    public void testGetAllClinics() {
        List<ClinicDTO> clinics = List.of(new ClinicDTO(), new ClinicDTO());
        when(clinicService.findAll()).thenReturn(clinics);

        ResponseEntity<List<ClinicDTO>> response = clinicController.getAllClinics();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetClinicById() {
        Long id = 1L;
        ClinicDTO dto = new ClinicDTO();
        dto.setId(id);
        when(clinicService.findById(id)).thenReturn(dto);

        ResponseEntity<ClinicDTO> response = clinicController.getClinicById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateClinic() {
        ClinicDTO dto = new ClinicDTO();
        dto.setName("Test Clinic");

        when(clinicService.save(dto)).thenReturn(dto);

        ResponseEntity<ClinicDTO> response = clinicController.createClinic(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Clinic", response.getBody().getName());
    }

    @Test
    public void testUpdateClinic() {
        Long id = 1L;
        ClinicDTO dto = new ClinicDTO();
        dto.setName("Updated Clinic");

        when(clinicService.save(dto)).thenReturn(dto);

        ResponseEntity<ClinicDTO> response = clinicController.updateClinic(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Clinic", response.getBody().getName());
    }

    @Test
    public void testDeleteClinic() {
        Long id = 1L;

        ResponseEntity<Void> response = clinicController.deleteClinic(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clinicService).deleteById(id);
    }
}

