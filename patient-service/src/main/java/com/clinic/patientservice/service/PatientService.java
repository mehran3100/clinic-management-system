package com.clinic.patientservice.service;

import com.clinic.patientservice.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO dto);
    List<PatientDTO> createBatch(List<PatientDTO> dtos);
    PatientDTO getPatientById(Long id);
    Page<PatientDTO> getAllPatients(Pageable pageable);
    PatientDTO updatePatient(Long id, PatientDTO dto);
    List<PatientDTO> updateBatch(List<PatientDTO> dtos);
    void deletePatient(Long id);
    void deleteBatch(List<Long> ids);

}
