package com.andrei.project_web.service;

import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.dto.ScheduleDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.ScheduleMapper;
import com.andrei.project_web.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO findById(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        return scheduleMapper.toDTO(schedule);
    }

    @Override
    public ScheduleDTO save(ScheduleDTO dto) {
        Schedule saved = scheduleRepository.save(scheduleMapper.toSchedule(dto));
        return scheduleMapper.toDTO(saved);
    }

    @Override
    public ScheduleDTO update(ScheduleDTO dto) {
        Schedule existing = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));

        existing.setScheduleDateTime(dto.getScheduleDateTime());

        return scheduleMapper.toDTO(scheduleRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}

