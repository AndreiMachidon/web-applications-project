package com.andrei.project_web.service;

import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.dto.ClinicDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.ClinicMapper;
import com.andrei.project_web.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicMapper clinicMapper) {
        this.clinicRepository = clinicRepository;
        this.clinicMapper = clinicMapper;
    }

    @Override
    public List<ClinicDTO> findAll() {
        return clinicRepository.findAll().stream()
                .map(clinicMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicDTO findById(long id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinic not found"));
        return clinicMapper.toDTO(clinic);
    }

    @Override
    public ClinicDTO save(ClinicDTO dto) {
        Clinic saved = clinicRepository.save(clinicMapper.toClinic(dto));
        return clinicMapper.toDTO(saved);
    }

    @Override
    public ClinicDTO update(ClinicDTO dto) {
        Clinic existing = clinicRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic not found"));
        existing.setName(dto.getName());
        existing.setLocation(dto.getLocation());
        existing.setPhoneNumber(dto.getPhoneNumber());
        return clinicMapper.toDTO(clinicRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        clinicRepository.deleteById(id);
    }
}
