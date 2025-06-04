package com.clinic.appointmentservice.service;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.commoncore.exception.ResourceNotFoundException;
import com.clinic.appointmentservice.feignclient.PatientClient;
import com.clinic.appointmentservice.mapper.AppointmentMapper;
import com.clinic.appointmentservice.model.Appointment;
import com.clinic.appointmentservice.repository.AppointmentRepository;
import com.clinic.appointmentservice.utility.DateUtil;
import com.clinic.commoncore.dto.AppointmentResponse;
import com.clinic.commoncore.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;
    private final PatientClient client;

    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper, PatientClient client) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
    }

    @Override
    public List<AppointmentDTO> createBatch(List<AppointmentDTO> dtos) {
        List<Appointment> entities = dtos.stream()
                .map(mapper::toEntity)
                .toList();

        List<Appointment> savedEntities = repository.saveAll(entities);

        return mapper.toDTOList(savedEntities);
    }

    @Override
    public AppointmentDTO create(AppointmentDTO dto) {
        Appointment saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    public Page<AppointmentDTO> getAllAppointments(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public AppointmentDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id -> {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Appointment not found with ID: " + id);
            }
        });
        repository.deleteAllById(ids);
    }

    @Override
    public AppointmentDTO update(Long id, AppointmentDTO dto) {
        Appointment existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        existing.setPatientId(dto.getPatientId());
        existing.setAppointmentDate(DateUtil.parse(dto.getAppointmentDate()));
        existing.setTimeSlot(dto.getTimeSlot());
        existing.setNotes(dto.getNotes());

        Appointment updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public List<AppointmentDTO> updateBatch(List<AppointmentDTO> dtos) {
        List<Appointment> updated = dtos.stream().map(dto -> {
            Appointment existing = repository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + dto.getId()));
            existing.setPatientId(dto.getPatientId());
            existing.setAppointmentDate(DateUtil.parse(dto.getAppointmentDate()));
            existing.setTimeSlot(dto.getTimeSlot());
            existing.setNotes(dto.getNotes());
            return existing;
        }).toList();

        return mapper.toDTOList(repository.saveAll(updated));
    }

    @Override
    public AppointmentResponse getAppointmentWithPatient(Long appointmentId) {
        Appointment appointment = repository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));

        PatientDTO patient = client.getPatientById(appointment.getPatientId());

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .timeSlot(appointment.getTimeSlot())
                .notes(appointment.getNotes())
                .patient(patient)
                .build();
    }

}
