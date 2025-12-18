package com.patient.helper.doctor.service;

import com.patient.helper.doctor.model.DoctorAssignedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * KafkaDoctorProducerService
 *
 * Publishes doctor assignment events to Kafka.
 */
@Service
public class KafkaDoctorProducerService {

    private final KafkaTemplate<String, DoctorAssignedEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.doctorAssigned}")
    private String doctorAssignedTopic;

    public KafkaDoctorProducerService(
            KafkaTemplate<String, DoctorAssignedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends doctor.assigned event to Kafka.
     *
     * @param event DoctorAssignedEvent
     */
    public void sendDoctorAssignedEvent(DoctorAssignedEvent event) {
        kafkaTemplate.send(doctorAssignedTopic, event.getPatientId(), event);
    }
}
