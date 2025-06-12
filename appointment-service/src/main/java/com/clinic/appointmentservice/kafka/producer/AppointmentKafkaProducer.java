package com.clinic.appointmentservice.kafka.producer;

import com.clinic.commonkafka.event.AppointmentCreatedEvent;
import com.clinic.commonkafka.event.PatientCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentKafkaProducer {

    private final KafkaTemplate<String, Object> kafka;

    public AppointmentKafkaProducer(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void send(AppointmentCreatedEvent event) {
        log.info("📤 Sending appointment event to Kafka: {}", event.getData().getAppointmentId());
        kafka.send("appointment-created", event);
    }

}
