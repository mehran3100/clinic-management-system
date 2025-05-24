package com.clinic.appointmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AppointmentDTO {

    private Long id;

    @NotNull(message = "patientId is required")
    private Long patientId;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format YYYY-MM-DD")
    private String appointmentDate;

    @NotNull(message = "timeSlot is required")
    private String timeSlot;

    private String notes;
}
