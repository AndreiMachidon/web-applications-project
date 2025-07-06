package com.andrei.project_web.services;

import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.mappers.ClinicMapper;
import com.andrei.project_web.repositories.ClinicRepository;
import com.andrei.project_web.service.ClinicServiceImpl;
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
public class ClinicServiceTest {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private ClinicMapper clinicMapper;

    @InjectMocks
    private ClinicServiceImpl clinicServiceImpl;

    @Test
    public void testFindAll() {
        when(clinicRepository.findAll()).thenReturn(List.of(new Clinic()));
        when(clinicMapper.toDTO(any())).thenReturn(new ClinicDTO());

        List<ClinicDTO> result = clinicServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(clinicRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Clinic entity = new Clinic();
        entity.setId(1L);
        when(clinicRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(clinicMapper.toDTO(entity)).thenReturn(new ClinicDTO());

        ClinicDTO result = clinicServiceImpl.findById(1L);

        assertNotNull(result);
        verify(clinicRepository).findById(1L);
    }

    @Test
    public void testSave() {
        ClinicDTO dto = new ClinicDTO();
        Clinic entity = new Clinic();

        when(clinicMapper.toClinic(dto)).thenReturn(entity);
        when(clinicRepository.save(entity)).thenReturn(entity);
        when(clinicMapper.toDTO(entity)).thenReturn(dto);

        ClinicDTO saved = clinicServiceImpl.save(dto);

        assertNotNull(saved);
        verify(clinicRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        clinicServiceImpl.deleteById(1L);
        verify(clinicRepository).deleteById(1L);
    }
}
