package com.clinic.appointmentservice.service;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.exception.ResourceNotFoundException;
import com.clinic.appointmentservice.mapper.AppointmentMapper;
import com.clinic.appointmentservice.model.Appointment;
import com.clinic.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public AppointmentDTO save(AppointmentDTO dto) {
        Appointment saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
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
        existing.setAppointmentDate(dto.getAppointmentDate());
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
            existing.setAppointmentDate(dto.getAppointmentDate());
            existing.setTimeSlot(dto.getTimeSlot());
            existing.setNotes(dto.getNotes());
            return existing;
        }).toList();

        return mapper.toDTOList(repository.saveAll(updated));
    }

}
