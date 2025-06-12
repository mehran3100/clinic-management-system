package com.clinic.recommendationservice.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.DeserializationException;

@Configuration
@Slf4j
public class KafkaErrorHandlerConfig {

    @Bean
    public CommonErrorHandler errorHandler() {
        return new DefaultErrorHandler((ConsumerRecord<?, ?> record, Exception ex) -> {
            if (ex.getCause() instanceof DeserializationException deserEx) {
                log.error("🔥 Deserialization failed for record: {}", new String(deserEx.getData()), deserEx);
            } else {
                log.error("🔥 Kafka error while processing: {}", record, ex);
            }
        });
    }
}
