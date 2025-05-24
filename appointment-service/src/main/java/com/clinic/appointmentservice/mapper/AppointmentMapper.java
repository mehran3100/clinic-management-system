package com.clinic.appointmentservice.mapper;

import com.clinic.appointmentservice.dto.AppointmentDTO;
import com.clinic.appointmentservice.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "appointmentDate", target = "appointmentDate", qualifiedByName = "stringToLocalDate")
    Appointment toEntity(AppointmentDTO dto);

    @Mapping(source = "appointmentDate", target = "appointmentDate", qualifiedByName = "localDateToString")
    AppointmentDTO toDTO(Appointment entity);

    List<AppointmentDTO> toDTOList(List<Appointment> entityList);

    @Named("stringToLocalDate")
    static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("localDateToString")
    static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
