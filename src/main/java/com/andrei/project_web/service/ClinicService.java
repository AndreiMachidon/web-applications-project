package com.andrei.project_web.service;

import com.andrei.project_web.dto.ClinicDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClinicService {
    List<ClinicDTO> findAll();
    ClinicDTO findById(long id);
    ClinicDTO save(ClinicDTO clinic);
    ClinicDTO update(ClinicDTO ClinicDTO);
    void deleteById(Long id);
}
