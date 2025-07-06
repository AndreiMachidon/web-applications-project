package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.service.NotificationService;
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
public class NotificationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificationService notificationService;

    private final Long fixedId = 1L;

    @Test
    public void testGetAllNotifications() throws Exception {
        NotificationDTO dto = NotificationDTO.builder().id(fixedId).message("Notificare 1").build();
        when(notificationService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].message").value("Notificare 1"));
    }

    @Test
    public void testGetNotificationById() throws Exception {
        NotificationDTO dto = NotificationDTO.builder().id(fixedId).message("Notificare 2").build();
        when(notificationService.findById(fixedId)).thenReturn(dto);

        mockMvc.perform(get("/api/notifications/{id}", fixedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Notificare 2"));
    }

    @Test
    public void testCreateNotification() throws Exception {
        NotificationDTO dto = NotificationDTO.builder().id(fixedId).message("Nouă notificare").build();
        when(notificationService.save(any())).thenReturn(dto);

        mockMvc.perform(post("/api/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "message": "Nouă notificare"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Nouă notificare"));
    }

    @Test
    public void testUpdateNotification() throws Exception {
        NotificationDTO dto = NotificationDTO.builder().id(fixedId).message("Actualizare notificare").build();
        when(notificationService.save(any())).thenReturn(dto);

        mockMvc.perform(put("/api/notifications/{id}", fixedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "message": "Actualizare notificare"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Actualizare notificare"));
    }

    @Test
    public void testDeleteNotification() throws Exception {
        mockMvc.perform(delete("/api/notifications/{id}", fixedId))
                .andExpect(status().isNoContent());

        verify(notificationService).deleteById(fixedId);
    }
}

