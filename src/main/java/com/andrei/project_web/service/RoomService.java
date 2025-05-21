package com.andrei.project_web.service;

import com.andrei.project_web.dto.PatientDTO;
import com.andrei.project_web.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<RoomDTO> findAll();
    RoomDTO findById(long id);
    RoomDTO save(RoomDTO medicalRecord);
    RoomDTO update(RoomDTO medicalRecord);
    void deleteById(Long id);
}
