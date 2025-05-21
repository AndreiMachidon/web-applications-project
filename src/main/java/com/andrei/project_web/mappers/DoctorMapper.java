package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.domain.Doctor;
import com.andrei.project_web.dto.ConsultationDTO;
import com.andrei.project_web.dto.DoctorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDTO toDTO(Doctor doctor);
    Doctor toDoctor(DoctorDTO doctorDTO);
}
