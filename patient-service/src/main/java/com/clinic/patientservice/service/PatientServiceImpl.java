package com.clinic.patientservice.service;

import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.model.Patient;
import com.clinic.patientservice.exception.ResourceNotFoundException;
import com.clinic.patientservice.mapper.PatientMapper;
import com.clinic.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.repository = patientRepository;
        this.mapper = patientMapper;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = mapper.toEntity(dto);
        Patient savedPatient = repository.save(patient);
        return mapper.toDto(savedPatient);
    }

    @Override
    public List<PatientDTO> createBatch(List<PatientDTO> dtos) {
        List<Patient> saved = repository.saveAll(
                dtos.stream().map(mapper::toEntity).toList()
        );
        return mapper.toDTOList(saved);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return mapper.toDto(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient existingPatient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        existingPatient.setFirstName(dto.getFirstName());
        existingPatient.setLastName(dto.getLastName());
        existingPatient.setEmail(dto.getEmail());
        existingPatient.setPhone(dto.getPhone());
        existingPatient.setAge(dto.getAge());
        existingPatient.setAddress(dto.getAddress());

        Patient updatedPatient = repository.save(existingPatient);
        return mapper.toDto(updatedPatient);
    }

    @Override
    public List<PatientDTO> updateBatch(List<PatientDTO> dtos) {
        List<Patient> updated = dtos.stream().map(dto -> {
            Patient existing = repository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + dto.getId()));
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setAge(dto.getAge());
            existing.setEmail(dto.getEmail());
            existing.setPhone(dto.getPhone());
            existing.setAddress(dto.getAddress());
            return existing;
        }).toList();
        return mapper.toDTOList(repository.saveAll(updated));
    }

    @Override
    public void deletePatient(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Patient not found with ID: " + id);
            }
        });
        repository.deleteAllById(ids);
    }

}
