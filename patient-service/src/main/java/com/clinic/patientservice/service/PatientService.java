package com.clinic.patientservice.service;

import com.clinic.patientservice.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO dto);
    List<PatientDTO> createBatch(List<PatientDTO> dtos);
    PatientDTO getPatientById(Long id);
    List<PatientDTO> getAllPatients();
    PatientDTO updatePatient(Long id, PatientDTO dto);
    List<PatientDTO> updateBatch(List<PatientDTO> dtos);
    void deletePatient(Long id);
    void deleteBatch(List<Long> ids);

}
