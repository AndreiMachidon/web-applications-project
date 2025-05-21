package com.andrei.project_web.service;

import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.dto.NotificationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationDTO> findAll();
    NotificationDTO findById(long id);
    NotificationDTO save(NotificationDTO medicalRecord);
    NotificationDTO update(NotificationDTO medicalRecord);
    void deleteById(Long id);
}
