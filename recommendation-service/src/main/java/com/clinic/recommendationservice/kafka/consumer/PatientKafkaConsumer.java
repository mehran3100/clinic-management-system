package com.clinic.recommendationservice.kafka.consumer;

import com.clinic.commonkafka.event.PatientCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientKafkaConsumer {

    @KafkaListener(topics = "patient-created", groupId = "recommendation-group")
    public void consume(PatientCreatedEvent event) {
        System.out.println("📥 Received patient created: " + event.getData().getFirstName());
        System.out.println("Detected pattern for " + event.getData().getFirstName() + " in past visits (Not yet implemented)");
    }
}
