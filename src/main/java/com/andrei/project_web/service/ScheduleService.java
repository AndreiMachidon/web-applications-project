package com.andrei.project_web.service;

import com.andrei.project_web.dto.RoomDTO;
import com.andrei.project_web.dto.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    List<ScheduleDTO> findAll();
    ScheduleDTO findById(long id);
    ScheduleDTO save(ScheduleDTO medicalRecord);
    ScheduleDTO update(ScheduleDTO medicalRecord);
    void deleteById(Long id);
}
