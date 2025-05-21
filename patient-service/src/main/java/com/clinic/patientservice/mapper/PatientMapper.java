package com.clinic.patientservice.mapper;

import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientDTO dto);
    PatientDTO toDto(Patient entity);
    List<PatientDTO> toDTOList(List<Patient> entityList);
}
