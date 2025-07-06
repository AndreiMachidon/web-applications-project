package com.andrei.project_web.services;

import com.andrei.project_web.domain.Schedule;
import com.andrei.project_web.dto.ScheduleDTO;
import com.andrei.project_web.mappers.ScheduleMapper;
import com.andrei.project_web.repositories.ScheduleRepository;
import com.andrei.project_web.service.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @InjectMocks
    private ScheduleServiceImpl scheduleServiceImpl;

    @Test
    public void testFindAll() {
        when(scheduleRepository.findAll()).thenReturn(List.of(new Schedule()));
        when(scheduleMapper.toDTO(any())).thenReturn(new ScheduleDTO());

        List<ScheduleDTO> result = scheduleServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Schedule entity = new Schedule();
        entity.setId(1L);
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(scheduleMapper.toDTO(entity)).thenReturn(new ScheduleDTO());

        ScheduleDTO result = scheduleServiceImpl.findById(1L);

        assertNotNull(result);
        verify(scheduleRepository).findById(1L);
    }

    @Test
    public void testSave() {
        ScheduleDTO dto = new ScheduleDTO();
        Schedule entity = new Schedule();

        when(scheduleMapper.toSchedule(dto)).thenReturn(entity);
        when(scheduleRepository.save(entity)).thenReturn(entity);
        when(scheduleMapper.toDTO(entity)).thenReturn(dto);

        ScheduleDTO saved = scheduleServiceImpl.save(dto);

        assertNotNull(saved);
        verify(scheduleRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        scheduleServiceImpl.deleteById(1L);
        verify(scheduleRepository).deleteById(1L);
    }
}
