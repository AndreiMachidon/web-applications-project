package com.andrei.project_web.service;

import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.dto.PatientDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.PatientMapper;
import com.andrei.project_web.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO findById(long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return patientMapper.toDTO(patient);
    }

    @Override
    public PatientDTO save(PatientDTO dto) {
        Patient saved = patientRepository.save(patientMapper.toPatient(dto));
        return patientMapper.toDTO(saved);
    }

    @Override
    public PatientDTO update(PatientDTO dto) {
        Patient existing = patientRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setBirthDate(dto.getBirthDate());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());

        return patientMapper.toDTO(patientRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}

