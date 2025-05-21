package com.andrei.project_web.service;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.dto.AppointmentDTO;
import com.andrei.project_web.exception.ResourceNotFoundException;
import com.andrei.project_web.mappers.AppointmentMapper;
import com.andrei.project_web.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this.appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Appointment not found!");
        }
        return appointmentMapper.toDto(appointmentOptional.get());
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.save(appointmentMapper.toAppointment(appointmentDTO));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Appointment existing = appointmentRepository.findById(appointmentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        existing.setStartTime(appointmentDTO.getStartTime());
        existing.setEndTime(appointmentDTO.getEndTime());
        existing.setStatus(appointmentDTO.getStatus());
        existing.setPatient(appointmentDTO.getPatient());
        existing.setDoctor(appointmentDTO.getDoctor());
        existing.setConsultation(appointmentDTO.getConsultation());

        return appointmentMapper.toDto(appointmentRepository.save(existing));
    }

    @Override
    public void deleteById(Long id) {
        this.appointmentRepository.deleteById(id);
    }
}
