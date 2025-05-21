package com.andrei.project_web.mappers;

import com.andrei.project_web.domain.MedicalRecord;
import com.andrei.project_web.dto.MedicalRecordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);
    MedicalRecord toMedicalRecord(MedicalRecordDTO medicalRecordDTO);
}
