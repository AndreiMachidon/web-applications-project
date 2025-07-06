package com.andrei.project_web.services;

import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.mappers.MedicalRecordMapper;
import com.andrei.project_web.repositories.MedicalRecordRepository;
import com.andrei.project_web.service.MedicalRecordServiceImpl;
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
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordServiceImpl;

    @Test
    public void testFindAll() {
        when(medicalRecordRepository.findAll()).thenReturn(List.of(new MedicalRecord()));
        when(medicalRecordMapper.toDTO(any())).thenReturn(new MedicalRecordDTO());

        List<MedicalRecordDTO> result = medicalRecordServiceImpl.findAll();

        assertEquals(1, result.size());
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        MedicalRecord entity = new MedicalRecord();
        entity.setId(1L);
        when(medicalRecordRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(medicalRecordMapper.toDTO(entity)).thenReturn(new MedicalRecordDTO());

        MedicalRecordDTO result = medicalRecordServiceImpl.findById(1L);

        assertNotNull(result);
        verify(medicalRecordRepository).findById(1L);
    }

    @Test
    public void testSave() {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        MedicalRecord entity = new MedicalRecord();

        when(medicalRecordMapper.toMedicalRecord(dto)).thenReturn(entity);
        when(medicalRecordRepository.save(entity)).thenReturn(entity);
        when(medicalRecordMapper.toDTO(entity)).thenReturn(dto);

        MedicalRecordDTO saved = medicalRecordServiceImpl.save(dto);

        assertNotNull(saved);
        verify(medicalRecordRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        medicalRecordServiceImpl.deleteById(1L);
    }
}
