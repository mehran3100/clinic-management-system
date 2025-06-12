package com.clinic.commonkafka.event;

import com.clinic.commonkafka.dto.PatientEventDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreatedEvent {
    private PatientEventDTO data;

}
