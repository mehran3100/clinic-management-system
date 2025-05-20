package com.clinic.patientservice.service;

import com.clinic.patientservice.dto.PatientDto;
import com.clinic.patientservice.entity.Patient;
import com.clinic.patientservice.exception.ResourceNotFoundException;
import com.clinic.patientservice.mapper.PatientMapper;
import com.clinic.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientDto createPatient(PatientDto dto) {
        Patient patient = patientMapper.toEntity(dto);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto dto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        existingPatient.setFirstName(dto.getFirstName());
        existingPatient.setLastName(dto.getLastName());
        existingPatient.setEmail(dto.getEmail());
        existingPatient.setPhone(dto.getPhone());
        existingPatient.setAge(dto.getAge());
        existingPatient.setAddress(dto.getAddress());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDto(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}
