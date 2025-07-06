package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.DoctorDTO;
import com.andrei.project_web.service.DoctorService;
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
public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @Test
    public void testGetAllDoctors() {
        List<DoctorDTO> doctors = List.of(new DoctorDTO(), new DoctorDTO());
        when(doctorService.findAll()).thenReturn(doctors);

        ResponseEntity<List<DoctorDTO>> response = doctorController.getAllDoctors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetDoctorById() {
        Long id = 1L;
        DoctorDTO dto = new DoctorDTO();
        dto.setId(id);
        when(doctorService.findById(id)).thenReturn(dto);

        ResponseEntity<DoctorDTO> response = doctorController.getDoctorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateDoctor() {
        DoctorDTO dto = new DoctorDTO();
        dto.setName("Dr. House");

        when(doctorService.save(dto)).thenReturn(dto);

        ResponseEntity<DoctorDTO> response = doctorController.createDoctor(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Dr. House", response.getBody().getName());
    }

    @Test
    public void testUpdateDoctor() {
        Long id = 1L;
        DoctorDTO dto = new DoctorDTO();
        dto.setName("Dr. Updated");

        when(doctorService.save(dto)).thenReturn(dto);

        ResponseEntity<DoctorDTO> response = doctorController.updateDoctor(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dr. Updated", response.getBody().getName());
    }

    @Test
    public void testDeleteDoctor() {
        Long id = 1L;

        ResponseEntity<Void> response = doctorController.deleteDoctor(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(doctorService).deleteById(id);
    }
}
