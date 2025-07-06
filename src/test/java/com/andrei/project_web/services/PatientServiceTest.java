package com.andrei.project_web.services;

import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.dto.PatientDTO;
import com.andrei.project_web.mappers.PatientMapper;
import com.andrei.project_web.repositories.PatientRepository;
import com.andrei.project_web.service.PatientService;
import com.andrei.project_web.service.PatientServiceImpl;
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
public class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Test
    public void testFindAll() {
        when(patientRepository.findAll()).thenReturn(List.of(new Patient()));
        when(patientMapper.toDTO(any())).thenReturn(new PatientDTO());

        List<PatientDTO> result = patientServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Patient entity = new Patient();
        entity.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(patientMapper.toDTO(entity)).thenReturn(new PatientDTO());

        PatientDTO result = patientServiceImpl.findById(1L);

        assertNotNull(result);
        verify(patientRepository).findById(1L);
    }

    @Test
    public void testSave() {
        PatientDTO dto = new PatientDTO();
        Patient entity = new Patient();

        when(patientMapper.toPatient(dto)).thenReturn(entity);
        when(patientRepository.save(entity)).thenReturn(entity);
        when(patientMapper.toDTO(entity)).thenReturn(dto);

        PatientDTO saved = patientServiceImpl.save(dto);

        assertNotNull(saved);
        verify(patientRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        patientServiceImpl.deleteById(1L);
        verify(patientRepository).deleteById(1L);
    }
}
