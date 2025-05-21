package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.Consultation;
import com.andrei.project_web.dto.ConsultationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    ConsultationDTO toDTO(Consultation consultation);
    Consultation toConsultation(ConsultationDTO consultationDTO);
}
