package com.andrei.project_web.service;

import com.andrei.project_web.dto.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentDTO> findAll();
    AppointmentDTO findById(long id);
    AppointmentDTO save(AppointmentDTO appointment);
    AppointmentDTO update(AppointmentDTO appointment);
    void deleteById(Long id);
}
