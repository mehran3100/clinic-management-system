package com.clinic.patientservice;

import com.clinic.commonkafka.config.KafkaProducerConfig;
import com.clinic.commonkafka.consumer.KafkaConsumerConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({KafkaProducerConfig.class, KafkaConsumerConfig.class})
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

	@PostConstruct
	public void logActiveProfile() {
		System.out.println("🔁 ACTIVE PROFILE = " + System.getProperty("spring.profiles.active"));
	}

}
