package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Notification;
import com.andrei.project_web.domain.Patient;
import com.andrei.project_web.dto.NotificationDTO;
import com.andrei.project_web.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDTO toDTO(Patient patient);
    Patient toPatient(PatientDTO patientDTO);
}
