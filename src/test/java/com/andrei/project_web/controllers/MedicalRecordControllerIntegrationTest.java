package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.service.MedicalRecordService;
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
public class MedicalRecordControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MedicalRecordService medicalRecordService;

    private final long fixedId = 1L;

    @Test
    public void testGetAllMedicalRecords() throws Exception {
        MedicalRecordDTO dto = MedicalRecordDTO.builder().id(fixedId).description("Initial Checkup").build();
        when(medicalRecordService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/medicalrecords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Initial Checkup"));
    }

    @Test
    public void testGetMedicalRecordById() throws Exception {
        MedicalRecordDTO dto = MedicalRecordDTO.builder()
                .id(fixedId)
                .description("Surgery Follow-up").build();
        when(medicalRecordService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/medicalrecords/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Surgery Follow-up"));
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {
        MedicalRecordDTO dto = MedicalRecordDTO.builder().id(fixedId).description("New Medical Record").build();
        when(medicalRecordService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/medicalrecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "details": "New Medical Record"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("New Medical Record"));
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        MedicalRecordDTO dto = MedicalRecordDTO.builder().id(fixedId).description("Updated Medical Record").build();
        when(medicalRecordService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/medicalrecords/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "details": "Updated Medical Record"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Medical Record"));
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/api/medicalrecords/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(medicalRecordService).deleteById(fixedId);
    }

}
