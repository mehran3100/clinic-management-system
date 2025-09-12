package com.clinic.patientservice;

import com.clinic.patientservice.dto.PatientDTO;
import com.clinic.patientservice.kafka.producer.PatientKafkaProducer;
import com.clinic.patientservice.mapper.PatientMapper;
import com.clinic.patientservice.model.Patient;
import com.clinic.patientservice.repository.PatientRepository;
import com.clinic.patientservice.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.clinic.commonkafka.event.PatientCreatedEvent;
import com.clinic.commoncore.exception.ResourceNotFoundException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientServiceImplTest {

    private PatientRepository repository;
    private PatientMapper mapper;
    private PatientKafkaProducer kafka;
    private PatientServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(PatientRepository.class);
        mapper = mock(PatientMapper.class);
        kafka = mock(PatientKafkaProducer.class);

        service = new PatientServiceImpl(repository, mapper, kafka);
    }

    @Test
    void testCreatePatient_success() {
        // Arrange
        PatientDTO dto = new PatientDTO(); // fill with test data as needed
        Patient entity = new Patient();
        Patient savedEntity = new Patient();
        PatientDTO savedDto = new PatientDTO();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDTO(savedEntity)).thenReturn(savedDto);
        new PatientCreatedEvent();
        when(mapper.toEventDTO(savedEntity)).thenReturn(PatientCreatedEvent.builder()
                .build().getData());

        // Act
        PatientDTO result = service.createPatient(dto);

        // Assert
        assertEquals(savedDto, result);
        verify(repository).save(entity);
        verify(kafka).send(any(PatientCreatedEvent.class));
    }

    @Test
    void testGetPatientById_found() {
        // Arrange
        Long id = 1L;
        Patient entity = new Patient();
        PatientDTO dto = new PatientDTO();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        // Act
        PatientDTO result = service.getPatientById(id);

        // Assert
        assertEquals(dto, result);
    }

    @Test
    void testGetPatientById_notFound() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> service.getPatientById(id));

        assertTrue(thrown.getMessage().contains("Patient not found"));
    }

    @Test
    void testDeletePatient_exists() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        service.deletePatient(id);

        verify(repository).deleteById(id);
    }

    @Test
    void testDeletePatient_notExists() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deletePatient(id));
    }
}
