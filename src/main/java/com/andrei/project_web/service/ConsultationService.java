package com.andrei.project_web.service;

import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.dto.ConsultationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultationService {
    List<ConsultationDTO> findAll();
    ConsultationDTO findById(long id);
    ConsultationDTO save(ConsultationDTO consultation);
    ConsultationDTO update(ConsultationDTO consultation);
    void deleteById(Long id);
}
