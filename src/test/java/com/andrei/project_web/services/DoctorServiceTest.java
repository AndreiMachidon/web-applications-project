package com.andrei.project_web.services;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.dto.DoctorDTO;
import com.andrei.project_web.mappers.DoctorMapper;
import com.andrei.project_web.repositories.DoctorRepository;
import com.andrei.project_web.service.DoctorServiceImpl;
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
public class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @Test
    public void testFindAll() {
        when(doctorRepository.findAll()).thenReturn(List.of(new Doctor()));
        when(doctorMapper.toDTO(any())).thenReturn(new DoctorDTO());

        List<DoctorDTO> result = doctorServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Doctor entity = new Doctor();
        entity.setId(1L);
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(doctorMapper.toDTO(entity)).thenReturn(new DoctorDTO());

        DoctorDTO result = doctorServiceImpl.findById(1L);

        assertNotNull(result);
        verify(doctorRepository).findById(1L);
    }

    @Test
    public void testSave() {
        DoctorDTO dto = new DoctorDTO();
        Doctor entity = new Doctor();

        when(doctorMapper.toDoctor(dto)).thenReturn(entity);
        when(doctorRepository.save(entity)).thenReturn(entity);
        when(doctorMapper.toDTO(entity)).thenReturn(dto);

        DoctorDTO saved = doctorServiceImpl.save(dto);

        assertNotNull(saved);
        verify(doctorRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        doctorServiceImpl.deleteById(1L);
        verify(doctorRepository).deleteById(1L);
    }
}
