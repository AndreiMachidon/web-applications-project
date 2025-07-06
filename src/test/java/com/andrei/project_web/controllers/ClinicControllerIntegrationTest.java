package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.service.ClinicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class ClinicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClinicService clinicService;

    private final Long fixedId = 1L;

    @Test
    public void testGetAllClinics() throws Exception {
        ClinicDTO dto = ClinicDTO.builder().id(fixedId).name("Clinic A").build();
        when(clinicService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/clinics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Clinic A"));
    }

    @Test
    public void testGetClinicById() throws Exception {
        ClinicDTO dto = ClinicDTO.builder().id(fixedId).name("Clinic B").build();
        when(clinicService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/clinics/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Clinic B"));
    }

    @Test
    public void testCreateClinic() throws Exception {
        ClinicDTO dto = ClinicDTO.builder().id(fixedId).name("New Clinic").build();
        when(clinicService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/clinics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status": "PENDING",
                                "startTime": "2025-07-04T10:00:00",
                                "endTime": "2025-07-04T11:00:00"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Clinic"));
    }

    @Test
    public void testUpdateClinic() throws Exception {
        ClinicDTO dto = ClinicDTO.builder().id(fixedId).name("Updated Clinic").build();
        when(clinicService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/clinics/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Updated Clinic"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Clinic"));
    }

    @Test
    public void testDeleteClinic() throws Exception {
        mockMvc.perform(delete("/api/clinics/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(clinicService).deleteById(fixedId);
    }
}

