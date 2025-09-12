package com.clinic.appointmentservice;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.feignclient.PatientClient;
import com.clinic.appointmentservice.kafka.producer.AppointmentKafkaProducer;
import com.clinic.appointmentservice.mapper.AppointmentMapper;
import com.clinic.appointmentservice.model.Appointment;
import com.clinic.appointmentservice.repository.AppointmentRepository;
import com.clinic.appointmentservice.service.AppointmentServiceImpl;
import com.clinic.commoncore.dto.AppointmentResponse;
import com.clinic.commoncore.dto.PatientDTO;
import com.clinic.commoncore.exception.ResourceNotFoundException;
import com.clinic.commonkafka.event.AppointmentCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    private AppointmentRepository repository;
    private AppointmentMapper mapper;
    private PatientClient client;
    private AppointmentKafkaProducer kafka;
    private AppointmentServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(AppointmentRepository.class);
        mapper = mock(AppointmentMapper.class);
        client = mock(PatientClient.class);
        kafka = mock(AppointmentKafkaProducer.class);
        service = new AppointmentServiceImpl(repository, mapper, client, kafka);
    }

    @Test
    void testCreateAppointment_success() {
        AppointmentDTO dto = new AppointmentDTO();
        Appointment entity = new Appointment();
        Appointment saved = new Appointment();
        AppointmentDTO savedDto = new AppointmentDTO();
        saved.setAppointmentDate(LocalDate.now());

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(savedDto);

        AppointmentDTO result = service.create(dto);

        assertEquals(savedDto, result);
        verify(kafka).send(any(AppointmentCreatedEvent.class));
    }

    @Test
    void testGetById_found() {
        Appointment entity = new Appointment();
        AppointmentDTO dto = new AppointmentDTO();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        AppointmentDTO result = service.getById(1L);

        assertEquals(dto, result);
    }

    @Test
    void testGetById_notFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void testDelete_exists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDelete_notExists() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
    }

    @Test
    void testGetAllAppointments() {
        Appointment entity = new Appointment();
        AppointmentDTO dto = new AppointmentDTO();
        Page<Appointment> page = new PageImpl<>(List.of(entity));

        when(repository.findAll(any(PageRequest.class))).thenReturn(page);
        when(mapper.toDTO(entity)).thenReturn(dto);

        Page<AppointmentDTO> result = service.getAllAppointments(PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void testGetAppointmentWithPatient() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatientId(2L);
        appointment.setAppointmentDate(LocalDate.now());

        PatientDTO patient = new PatientDTO();

        when(repository.findById(1L)).thenReturn(Optional.of(appointment));
        when(client.getPatientById(2L)).thenReturn(patient);

        AppointmentResponse response = service.getAppointmentWithPatient(1L);

        assertEquals(appointment.getId(), response.getId());
        assertEquals(patient, response.getPatient());
    }
}
