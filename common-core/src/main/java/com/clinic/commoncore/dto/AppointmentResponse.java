package com.clinic.commoncore.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponse {
    private Long id;
    private LocalDate appointmentDate;
    private String timeSlot;
    private String notes;
    private PatientDTO patient;
}
