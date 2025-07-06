package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.DoctorDTO;
import com.andrei.project_web.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class DoctorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DoctorService doctorService;

    private final long fixedId = 1L;

    @Test
    public void testGetAllDoctors() throws Exception {
        DoctorDTO dto = DoctorDTO.builder().id(fixedId).name("Dr. A").build();
        when(doctorService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Dr. A"));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        DoctorDTO dto = DoctorDTO.builder().id(fixedId).name("Dr. B").build();
        when(doctorService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/doctors/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. B"));
    }

    @Test
    public void testCreateDoctor() throws Exception {
        DoctorDTO dto = DoctorDTO.builder().id(fixedId).name("Dr. C").build();
        when(doctorService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Dr. C"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dr. C"));
    }

}
