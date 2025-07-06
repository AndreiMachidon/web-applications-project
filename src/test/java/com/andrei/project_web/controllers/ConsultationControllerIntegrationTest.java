package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.service.ConsultationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
public class ConsultationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsultationService consultationService;

    private final long fixedId = 1L;

    @Test
    public void testGetAllConsultations() throws Exception {
        ConsultationDTO dto = ConsultationDTO.builder().id(fixedId).summary("Check-up").build();
        when(consultationService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/consultations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].summary").value("Check-up"));
    }

    @Test
    public void testGetConsultationById() throws Exception {
        ConsultationDTO dto = ConsultationDTO.builder().id(fixedId).summary("Follow-up").build();
        when(consultationService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/consultations/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary").value("Follow-up"));
    }

    @Test
    public void testCreateConsultation() throws Exception {
        ConsultationDTO dto = ConsultationDTO.builder().id(fixedId).summary("New Consultation").build();
        when(consultationService.save(any())).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/consultations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "reason": "New Consultation"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.summary").value("New Consultation"));
    }

    @Test
    public void testUpdateConsultation() throws Exception {
        ConsultationDTO dto = ConsultationDTO.builder().id(fixedId).summary("Updated Reason").build();
        when(consultationService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/consultations/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "reason": "Updated Reason"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summary").value("Updated Reason"));
    }

    @Test
    public void testDeleteConsultation() throws Exception {
        mockMvc.perform(delete("/api/consultations/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(consultationService).deleteById(fixedId);
    }
}
