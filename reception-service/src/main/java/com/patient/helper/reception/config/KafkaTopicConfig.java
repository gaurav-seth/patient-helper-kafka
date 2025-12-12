package com.patient.helper.reception.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * KafkaTopicConfig
 *
 * This configuration class is responsible for ensuring that the Kafka topic
 * required by the Reception microservice exists when the application starts.
 *
 * This microservice PUBLISHES the event:
 * - patient.registered
 *
 * Therefore, it is responsible for defining and creating the topic with the
 * desired partition count and replication factor.
 *
 * Why this is needed:
 * --------------------
 * - Prevents runtime errors if topic does not exist
 * - Ensures consistent topic configuration across environments
 * - Avoids manual CLI topic creation during development
 * - Properly aligns with microservice bounded context ownership
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Injects the topic name from application.yml.
     * Example:
     * spring.kafka.topic.patientRegistered: patient.registered
     */
    @Value("${spring.kafka.topic.patientRegistered}")
    private String patientRegisteredTopicName;

    /**
     * Creates the Kafka topic patient.registered if it does not already exist.
     *
     * @return NewTopic object that Spring Kafka Admin uses to create the topic
     */
    @Bean
    public NewTopic patientRegisteredTopic() {
        return TopicBuilder.name(patientRegisteredTopicName)
                .partitions(3)           // You can change partition count as per load
                .replicas(1)    // Single broker in local dev setup
                .build();
    }
}
