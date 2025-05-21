package com.andrei.project_web.service;

import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.dto.DoctorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    List<DoctorDTO> findAll();
    DoctorDTO findById(long id);
    DoctorDTO save(DoctorDTO consultation);
    DoctorDTO update(DoctorDTO consultation);
    void deleteById(Long id);
}
