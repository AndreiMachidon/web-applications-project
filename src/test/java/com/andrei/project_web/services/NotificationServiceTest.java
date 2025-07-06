package com.andrei.project_web.services;

import com.andrei.project_web.domain.Notification;
import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.mappers.NotificationMapper;
import com.andrei.project_web.repositories.NotificationRepository;
import com.andrei.project_web.service.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @InjectMocks
    private NotificationServiceImpl notificationServiceImpl;

    @Test
    public void testFindAll() {
        when(notificationRepository.findAll()).thenReturn(List.of(new Notification()));
        when(notificationMapper.toDTO(any())).thenReturn(new NotificationDTO());

        List<NotificationDTO> result = notificationServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(notificationRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Notification entity = new Notification();
        entity.setId(1L);
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(notificationMapper.toDTO(entity)).thenReturn(new NotificationDTO());

        NotificationDTO result = notificationServiceImpl.findById(1L);

        assertNotNull(result);
        verify(notificationRepository).findById(1L);
    }

    @Test
    public void testSave() {
        NotificationDTO dto = new NotificationDTO();
        Notification entity = new Notification();

        when(notificationMapper.toNotification(dto)).thenReturn(entity);
        when(notificationRepository.save(entity)).thenReturn(entity);
        when(notificationMapper.toDTO(entity)).thenReturn(dto);

        NotificationDTO saved = notificationServiceImpl.save(dto);

        assertNotNull(saved);
        verify(notificationRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        notificationServiceImpl.deleteById(1L);
        verify(notificationRepository).deleteById(1L);
    }
}
