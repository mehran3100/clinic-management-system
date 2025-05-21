package com.clinic.patientservice.mapper;

import com.clinic.patientservice.dto.PatientDto;
import com.clinic.patientservice.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientDto dto);
    PatientDto toDto(Patient entity);
}
