package com.clinic.commonkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppointmentEventDTO {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String appointmentDate;
    private String timeSlot;

}
