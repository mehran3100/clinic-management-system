package com.clinic.commonkafka.event;

import com.clinic.commonkafka.dto.PatientEventDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientCreatedEvent {
    private PatientEventDTO data;

    public PatientCreatedEvent() {}

    public PatientCreatedEvent(PatientEventDTO data) {
        this.data = data;
    }

}
