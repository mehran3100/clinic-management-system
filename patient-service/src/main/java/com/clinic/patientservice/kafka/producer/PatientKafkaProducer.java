package com.clinic.patientservice.kafka.producer;


import com.clinic.commonkafka.event.PatientCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientKafkaProducer {

    private final KafkaTemplate<String, Object> kafka;

    public PatientKafkaProducer(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    public void send(PatientCreatedEvent event) {
        log.info("📤 Sending patient created event to Kafka: {}", event.getData().getFirstName());
        kafka.send("patient-created", event);

    }
}
