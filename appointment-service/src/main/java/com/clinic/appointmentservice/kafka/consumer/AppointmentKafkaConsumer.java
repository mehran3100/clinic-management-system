package com.clinic.appointmentservice.kafka.consumer;

import com.clinic.commonkafka.event.PatientCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentKafkaConsumer {

    @KafkaListener(topics = "patient-created", groupId = "appointment-group")
    public void consume(PatientCreatedEvent event) {
        System.out.println("Received patient created: " + event.getData().getFirstName());
    }
}
