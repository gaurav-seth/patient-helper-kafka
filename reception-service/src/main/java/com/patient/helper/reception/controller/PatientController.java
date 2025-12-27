package com.patient.helper.reception.controller;

import com.patient.helper.events.PatientRegisteredEvent;
import com.patient.helper.reception.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

/**
 * PatientController
 *
 * This REST controller exposes endpoints for managing patient-related actions
 * in the Reception microservice. It handles incoming registration requests,
 * transforms them into PatientRegisteredEvent objects, and delegates event
 * publishing to KafkaProducerService.
 *
 * Endpoint:
 * POST /patients/register
 *
 * Responsibilities:
 * - Accept patient details from the client
 * - Generate unique patientId
 * - Add timestamp
 * - Publish event to Kafka
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final KafkaProducerService producerService;

    /**
     * Constructor injection ensures that KafkaProducerService is properly
     * initialized by Spring.
     *
     * @param producerService Service responsible for sending events to Kafka
     */
    @Autowired
    public PatientController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Registers a new patient in the system.
     * This method accepts patient details in the request body,
     * builds a PatientRegisteredEvent object, and publishes it to Kafka.
     *
     * @param request The incoming patient registration request
     * @return A confirmation message including the generated patientId
     */
    @PostMapping("/register")
    public String registerPatient(@RequestBody PatientRequest request) {

        String patientId = UUID.randomUUID().toString();  // unique ID
        long timestamp = Instant.now().toEpochMilli();

        PatientRegisteredEvent event =
        	    PatientRegisteredEvent.newBuilder()
        	        .setPatientId(patientId)
        	        .setName(request.getName())
        	        .setAge(request.getAge())
        	        .setPhone(request.getPhone())
        	        .setTimestamp(timestamp)
        	        .setPolicyVersion("v1")
        	        .build();

        producerService.sendPatientRegisteredEvent(event);

        return "Patient registered successfully with ID: " + patientId;
    }
}
