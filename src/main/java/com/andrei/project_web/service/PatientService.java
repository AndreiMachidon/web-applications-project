package com.andrei.project_web.service;

import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientDTO> findAll();
    PatientDTO findById(long id);
    PatientDTO save(PatientDTO medicalRecord);
    PatientDTO update(PatientDTO medicalRecord);
    void deleteById(Long id);
}
