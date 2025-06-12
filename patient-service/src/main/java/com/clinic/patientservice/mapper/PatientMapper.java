package com.clinic.patientservice.mapper;

import com.clinic.commonkafka.dto.PatientEventDTO;
import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toEntity(PatientDTO dto);
    PatientDTO toDTO(Patient entity);
    List<PatientDTO> toDTOList(List<Patient> entityList);

    default PatientEventDTO toEventDTO(Patient entity) {
        return PatientEventDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .build();
    }
}
