package com.andrei.project_web.service;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.ConsultationMapper;
import com.andrei.project_web.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, ConsultationMapper consultationMapper) {
        this.consultationRepository = consultationRepository;
        this.consultationMapper = consultationMapper;
    }

    @Override
    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationDTO findById(long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));
        return consultationMapper.toDTO(consultation);
    }

    @Override
    public ConsultationDTO save(ConsultationDTO consultationDTO) {
        Consultation saved = consultationRepository.save(consultationMapper.toConsultation(consultationDTO));
        return consultationMapper.toDTO(saved);
    }

    @Override
    public ConsultationDTO update(ConsultationDTO consultationDTO) {
        Consultation existing = consultationRepository.findById(consultationDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        existing.setSummary(consultationDTO.getSummary());
        existing.setPrescription(consultationDTO.getPrescription());
        existing.setFollowUpNeeded(consultationDTO.isFollowUpNeeded());

        Consultation updated = consultationRepository.save(existing);
        return consultationMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        consultationRepository.deleteById(id);
    }
}

