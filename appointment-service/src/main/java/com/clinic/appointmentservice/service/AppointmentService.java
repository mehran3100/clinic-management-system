package com.clinic.appointmentservice.service;

import com.clinic.appointmentservice.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO save(AppointmentDTO dto);
    List<AppointmentDTO> getAll();
    AppointmentDTO getById(Long id);
    void delete(Long id);
    AppointmentDTO update(Long id, AppointmentDTO dto);
}
