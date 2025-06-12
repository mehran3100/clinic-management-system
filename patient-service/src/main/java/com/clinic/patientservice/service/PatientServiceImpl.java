package com.clinic.patientservice.service;

import com.clinic.commoncore.exception.ResourceNotFoundException;
import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.kafka.producer.PatientKafkaProducer;
import com.clinic.patientservice.mapper.PatientMapper;
import com.clinic.patientservice.model.Patient;
import com.clinic.patientservice.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.clinic.commonkafka.event.PatientCreatedEvent;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final PatientKafkaProducer kafka;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, PatientKafkaProducer kafka) {
        this.repository = patientRepository;
        this.mapper = patientMapper;
        this.kafka = kafka;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);

        var event = PatientCreatedEvent.builder()
                .data(mapper.toEventDTO(saved))
                .build();
        kafka.send(event);
        return mapper.toDTO(saved);
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
        return mapper.toDTO(patient);
    }

    @Override
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
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
        return mapper.toDTO(updatedPatient);
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
