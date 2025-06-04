package com.clinic.commonkafka.event;

import com.clinic.commonkafka.dto.AppointmentEventDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreatedEvent {
    private AppointmentEventDTO data;

}
