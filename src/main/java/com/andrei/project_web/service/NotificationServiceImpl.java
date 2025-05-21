package com.andrei.project_web.service;

import com.andrei.project_web.domain.Notification;
import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.NotificationMapper;
import com.andrei.project_web.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationDTO> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO findById(long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return notificationMapper.toDTO(notification);
    }

    @Override
    public NotificationDTO save(NotificationDTO dto) {
        Notification saved = notificationRepository.save(notificationMapper.toNotification(dto));
        return notificationMapper.toDTO(saved);
    }

    @Override
    public NotificationDTO update(NotificationDTO dto) {
        Notification existing = notificationRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        existing.setMessage(dto.getMessage());
        existing.setSentAt(dto.getSentAt());
        existing.setRead(dto.isRead());

        return notificationMapper.toDTO(notificationRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
