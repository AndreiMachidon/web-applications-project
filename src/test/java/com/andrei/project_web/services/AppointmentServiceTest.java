package com.andrei.project_web.services;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.dto.AppointmentDTO;
import com.andrei.project_web.mappers.AppointmentMapper;
import com.andrei.project_web.repositories.AppointmentRepository;
import com.andrei.project_web.service.AppointmentServiceImpl;
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
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;

    @Test
    public void testFindAll() {
        when(appointmentRepository.findAll()).thenReturn(List.of(new Appointment()));
        when(appointmentMapper.toDto(any())).thenReturn(new AppointmentDTO());

        List<AppointmentDTO> result = appointmentServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Appointment entity = new Appointment();
        entity.setId(1L);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(appointmentMapper.toDto(entity)).thenReturn(new AppointmentDTO());

        AppointmentDTO result = appointmentServiceImpl.findById(1L);

        assertNotNull(result);
        verify(appointmentRepository).findById(1L);
    }

    @Test
    public void testSave() {
        AppointmentDTO dto = new AppointmentDTO();
        Appointment entity = new Appointment();

        when(appointmentMapper.toAppointment(dto)).thenReturn(entity);
        when(appointmentRepository.save(entity)).thenReturn(entity);
        when(appointmentMapper.toDto(entity)).thenReturn(dto);

        AppointmentDTO saved = appointmentServiceImpl.save(dto);

        assertNotNull(saved);
        verify(appointmentRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        appointmentServiceImpl.deleteById(1L);
        verify(appointmentRepository).deleteById(1L);
    }
}
