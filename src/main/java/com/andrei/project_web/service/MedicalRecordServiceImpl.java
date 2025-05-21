package com.andrei.project_web.service;

import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.dto.MedicalRecordDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.MedicalRecordMapper;
import com.andrei.project_web.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, MedicalRecordMapper medicalRecordMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicalRecordMapper = medicalRecordMapper;
    }

    @Override
    public List<MedicalRecordDTO> findAll() {
        return medicalRecordRepository.findAll().stream()
                .map(medicalRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO findById(long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));
        return medicalRecordMapper.toDTO(record);
    }

    @Override
    public MedicalRecordDTO save(MedicalRecordDTO dto) {
        MedicalRecord saved = medicalRecordRepository.save(medicalRecordMapper.toMedicalRecord(dto));
        return medicalRecordMapper.toDTO(saved);
    }

    @Override
    public MedicalRecordDTO update(MedicalRecordDTO dto) {
        MedicalRecord existing = medicalRecordRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));

        existing.setDescription(dto.getDescription());
        existing.setDiagnosis(dto.getDiagnosis());
        existing.setCreationDate(dto.getCreationDate());

        return medicalRecordMapper.toDTO(medicalRecordRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}
