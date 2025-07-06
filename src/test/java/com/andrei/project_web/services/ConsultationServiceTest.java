package com.andrei.project_web.services;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.mappers.ConsultationMapper;
import com.andrei.project_web.repositories.ConsultationRepository;
import com.andrei.project_web.service.ConsultationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ConsultationServiceTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private ConsultationMapper consultationMapper;

    @InjectMocks
    private ConsultationServiceImpl consultationServiceImpl;

    @Test
    public void testFindAll() {
        when(consultationRepository.findAll()).thenReturn(List.of(new Consultation()));
        when(consultationMapper.toDTO(any())).thenReturn(new ConsultationDTO());

        List<ConsultationDTO> result = consultationServiceImpl.findAll();

        Assertions.assertEquals(1, result.size());
        verify(consultationRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Consultation entity = new Consultation();
        entity.setId(1L);
        when(consultationRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(consultationMapper.toDTO(entity)).thenReturn(new ConsultationDTO());

        ConsultationDTO result = consultationServiceImpl.findById(1L);

        assertNotNull(result);
        verify(consultationRepository).findById(1L);
    }

    @Test
    public void testSave() {
        ConsultationDTO dto = new ConsultationDTO();
        Consultation entity = new Consultation();

        when(consultationMapper.toConsultation(dto)).thenReturn(entity);
        when(consultationRepository.save(entity)).thenReturn(entity);
        when(consultationMapper.toDTO(entity)).thenReturn(dto);

        ConsultationDTO saved = consultationServiceImpl.save(dto);

        assertNotNull(saved);
        verify(consultationRepository).save(entity);
    }

    @Test
    public void testDeleteById() {
        consultationServiceImpl.deleteById(1L);
        verify(consultationRepository).deleteById(1L);
    }
}
