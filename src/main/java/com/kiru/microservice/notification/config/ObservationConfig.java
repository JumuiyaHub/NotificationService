package com.kiru.microservice.notification.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class ObservationConfig {
    private final ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory;

    public ObservationConfig(ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory) {
        this.concurrentKafkaListenerContainerFactory = concurrentKafkaListenerContainerFactory;
    }

    @PostConstruct
    public void setObservationForKafkaTemplate() {
        concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }
    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}
