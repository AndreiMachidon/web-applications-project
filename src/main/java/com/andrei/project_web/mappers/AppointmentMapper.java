package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Appointment;
import com.andrei.project_web.dto.AppointmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDTO toDto(Appointment appointment);

    Appointment toAppointment(AppointmentDTO appointmentDTO);
}
