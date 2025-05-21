package com.andrei.project_web.service;

import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.dto.DoctorDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.DoctorMapper;
import com.andrei.project_web.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDTO> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO findById(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        return doctorMapper.toDTO(doctor);
    }

    @Override
    public DoctorDTO save(DoctorDTO dto) {
        Doctor saved = doctorRepository.save(doctorMapper.toDoctor(dto));
        return doctorMapper.toDTO(saved);
    }

    @Override
    public DoctorDTO update(DoctorDTO dto) {
        Doctor existing = doctorRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setSpecialization(dto.getSpecialization());
        existing.setLicenseNumber(dto.getLicenseNumber());

        return doctorMapper.toDTO(doctorRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}

