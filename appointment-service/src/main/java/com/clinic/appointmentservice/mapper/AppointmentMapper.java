package com.clinic.appointmentservice.mapper;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.model.Appointment;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment toEntity(AppointmentDTO dto);
    AppointmentDTO toDTO(Appointment entity);
    List<AppointmentDTO> toDTOList(List<Appointment> entityList);
}
