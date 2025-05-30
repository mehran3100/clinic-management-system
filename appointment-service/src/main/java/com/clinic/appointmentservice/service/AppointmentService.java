package com.clinic.appointmentservice.service;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.commoncore.dto.AppointmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO save(AppointmentDTO dto);
    List<AppointmentDTO> createBatch(List<AppointmentDTO> dtos);
    Page<AppointmentDTO> getAllAppointments(Pageable pageable);
    AppointmentDTO getById(Long id);
    void delete(Long id);
    void deleteBatch(List<Long> ids);
    AppointmentDTO update(Long id, AppointmentDTO dto);
    List<AppointmentDTO> updateBatch(List<AppointmentDTO> dtos);
    AppointmentResponse getAppointmentWithPatient(Long appointmentId);

}
