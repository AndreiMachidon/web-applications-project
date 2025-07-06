package com.andrei.project_web.controllers;

import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.service.NotificationService;
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
public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    public void testGetAllNotifications() {
        List<NotificationDTO> notifications = List.of(new NotificationDTO(), new NotificationDTO());
        when(notificationService.findAll()).thenReturn(notifications);

        ResponseEntity<List<NotificationDTO>> response = notificationController.getAllNotifications();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetNotificationById() {
        Long id = 1L;
        NotificationDTO dto = new NotificationDTO();
        dto.setId(id);
        when(notificationService.findById(id)).thenReturn(dto);

        ResponseEntity<NotificationDTO> response = notificationController.getNotificationById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testCreateNotification() {
        NotificationDTO dto = new NotificationDTO();
        dto.setMessage("Test Notification");

        when(notificationService.save(dto)).thenReturn(dto);

        ResponseEntity<NotificationDTO> response = notificationController.createNotification(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Notification", response.getBody().getMessage());
    }

    @Test
    public void testUpdateNotification() {
        Long id = 1L;
        NotificationDTO dto = new NotificationDTO();
        dto.setMessage("Updated Notification");

        when(notificationService.save(dto)).thenReturn(dto);

        ResponseEntity<NotificationDTO> response = notificationController.updateNotification(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Notification", response.getBody().getMessage());
    }

    @Test
    public void testDeleteNotification() {
        Long id = 1L;

        ResponseEntity<Void> response = notificationController.deleteNotification(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(notificationService).deleteById(id);
    }
}
