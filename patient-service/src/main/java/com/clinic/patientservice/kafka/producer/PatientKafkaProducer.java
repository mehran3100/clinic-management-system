package com.clinic.patientservice.kafka.producer;


import com.clinic.commonkafka.event.PatientCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientKafkaProducer {

    private final KafkaTemplate<String, Object> kafka;

    public PatientKafkaProducer(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void send(PatientCreatedEvent event) {
        System.out.println("📤 Sending event to Kafka: " + event);
        kafka.send("patient-created", event);
    }
}
