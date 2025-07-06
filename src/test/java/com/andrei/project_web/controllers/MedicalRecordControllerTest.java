package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.service.MedicalRecordService;
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
public class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Test
    public void testGetAllMedicalRecords() {
        List<MedicalRecordDTO> records = List.of(new MedicalRecordDTO(), new MedicalRecordDTO());
        when(medicalRecordService.findAll()).thenReturn(records);

        ResponseEntity<List<MedicalRecordDTO>> response = medicalRecordController.getAllMedicalRecords();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetMedicalRecordById() {
        Long id = 1L;
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(id);
        when(medicalRecordService.findById(id)).thenReturn(dto);

        ResponseEntity<MedicalRecordDTO> response = medicalRecordController.getMedicalRecordById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateMedicalRecord() {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setDescription("New Record");

        when(medicalRecordService.save(dto)).thenReturn(dto);

        ResponseEntity<MedicalRecordDTO> response = medicalRecordController.createMedicalRecord(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Record", response.getBody().getDescription());
    }

    @Test
    public void testUpdateMedicalRecord() {
        Long id = 1L;
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setDescription("Updated Record");

        when(medicalRecordService.save(dto)).thenReturn(dto);

        ResponseEntity<MedicalRecordDTO> response = medicalRecordController.updateMedicalRecord(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Record", response.getBody().getDescription());
    }

    @Test
    public void testDeleteMedicalRecord() {
        Long id = 1L;

        ResponseEntity<Void> response = medicalRecordController.deleteMedicalRecord(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(medicalRecordService).deleteById(id);
    }
}
