package com.andrei.project_web.service;

import com.andrei.project_web.dto.DoctorDTO;
import com.andrei.project_web.dto.MedicalRecordDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalRecordService {
    List<MedicalRecordDTO> findAll();
    MedicalRecordDTO findById(long id);
    MedicalRecordDTO save(MedicalRecordDTO medicalRecord);
    MedicalRecordDTO update(MedicalRecordDTO medicalRecord);
    void deleteById(Long id);
}
