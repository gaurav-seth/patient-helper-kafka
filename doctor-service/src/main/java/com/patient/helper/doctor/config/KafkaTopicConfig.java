package com.patient.helper.doctor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * KafkaTopicConfig
 *
 * This configuration ensures that the Kafka topic
 * "doctor.assigned" exists when doctor-service starts.
 *
 * Ownership:
 * doctor-service is the PRODUCER of doctor.assigned,
 * so it is responsible for creating this topic.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Topic name injected from application.yml
     */
    @Value("${spring.kafka.topic.doctorAssigned}")
    private String doctorAssignedTopic;

    /**
     * Creates the doctor.assigned topic if it does not exist.
     *
     * @return NewTopic definition
     */
    @Bean
    public NewTopic doctorAssignedTopic() {
        return TopicBuilder.name(doctorAssignedTopic)
                .partitions(3)   // allows parallel consumption later
                .replicas(1)     // single broker in local Docker setup
                .build();
    }
}
