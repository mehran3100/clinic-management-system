package com.clinic.appointmentservice.kafka.producer;

import com.clinic.commonkafka.event.AppointmentCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentKafkaProducer {

    private final KafkaTemplate<String, Object> kafka;

    public AppointmentKafkaProducer(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void send(AppointmentCreatedEvent event) {
        System.out.println("📤 Sending appointment event to Kafka: " + event.getData().getAppointmentId());
        kafka.send("appointment-created", event);
    }
}
