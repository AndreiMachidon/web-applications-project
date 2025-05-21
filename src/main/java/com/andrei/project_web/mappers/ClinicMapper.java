package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Clinic;
import com.andrei.project_web.dto.ClinicDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClinicMapper {
    ClinicDTO toDTO(Clinic clinic);
    Clinic toClinic(ClinicDTO clinicDto);

}
