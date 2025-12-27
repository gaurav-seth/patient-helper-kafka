package com.patient.helper.doctor.service;

import com.patient.helper.doctor.model.DoctorAssignedEvent;
import com.patient.helper.doctor.model.TestOrderedEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * KafkaDoctorProducerService
 *
 * Publishes doctor and test-related events to Kafka.
 */
@Service
public class KafkaDoctorProducerService {

    private final KafkaTemplate<String, DoctorAssignedEvent> doctorKafkaTemplate;
    private final KafkaTemplate<String, TestOrderedEvent> testKafkaTemplate;

    @Value("${spring.kafka.topic.doctorAssigned}")
    private String doctorAssignedTopic;

    @Value("${spring.kafka.topic.testOrdered}")
    private String testOrderedTopic;

    public KafkaDoctorProducerService(
            KafkaTemplate<String, DoctorAssignedEvent> doctorKafkaTemplate,
            KafkaTemplate<String, TestOrderedEvent> testKafkaTemplate) {

        this.doctorKafkaTemplate = doctorKafkaTemplate;
        this.testKafkaTemplate = testKafkaTemplate;
    }

    /**
     * Sends doctor.assigned event to Kafka.
     */
    public void sendDoctorAssignedEvent(DoctorAssignedEvent event) {
        doctorKafkaTemplate.send(doctorAssignedTopic, event.getPatientId(), event);
    }

    /**
     * Sends test.ordered event to Kafka.
     */
    public void sendTestOrderedEvent(TestOrderedEvent event) {
        testKafkaTemplate.send(testOrderedTopic, event.getPatientId(), event);
    }
}
