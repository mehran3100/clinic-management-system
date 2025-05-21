package com.clinic.appointmentservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {

    private Long id;

    @NotNull(message = "patientId is required")
    private Long patientId;

    @NotNull(message = "appointmentDate is required")
    private LocalDate appointmentDate;

    @NotNull(message = "timeSlot is required")
    private String timeSlot;

    private String notes;
}
