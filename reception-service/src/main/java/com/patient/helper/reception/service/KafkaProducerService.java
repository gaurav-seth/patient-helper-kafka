package com.patient.helper.reception.service;

import com.patient.helper.events.PatientRegisteredEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * KafkaProducerService
 *
 * This service is responsible for publishing PatientRegisteredEvent messages
 * to the configured Kafka topic. It acts as an abstraction layer between
 * the controller and KafkaTemplate.
 *
 * Responsibilities:
 * - Accept event objects from the controller
 * - Send the event to the appropriate Kafka topic
 * - Use patientId as the message key for partitioning
 */
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.patientRegistered}")
    private String topicName;   // Injected from application.yml

    /**
     * Constructor-based dependency injection ensures kafkaTemplate
     * is properly initialized by Spring.
     *
     * @param kafkaTemplate Kafka helper object for publishing messages
     */
    public KafkaProducerService(KafkaTemplate<String, PatientRegisteredEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a PatientRegisteredEvent to the Kafka topic defined in application.yml.
     * The patientId is used as the message key to ensure ordering for a given patient.
     *
     * @param event The event containing patient registration details
     */
    public void sendPatientRegisteredEvent(PatientRegisteredEvent event) {
        kafkaTemplate.send(topicName, event.getPatientId().toString(), event);
        System.out.println("Published event to Kafka: " + event);
    }
}
