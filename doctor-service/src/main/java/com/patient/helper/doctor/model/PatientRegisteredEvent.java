package com.patient.helper.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PatientRegisteredEvent
 *
 * This event represents a patient registration message
 * consumed by doctor-service from the Kafka topic "patient.registered".
 *
 * IMPORTANT:
 * - This class MUST match the JSON structure produced by reception-service
 * - Microservices do NOT share code, but they MUST share the event contract
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisteredEvent {

    /**
     * Unique identifier of the patient
     */
    private String patientId;

    /**
     * Patient full name
     */
    private String name;

    /**
     * Patient age
     */
    private Integer age;

    /**
     * Patient phone number
     */
    private String phone;

    /**
     * Event creation timestamp (epoch millis)
     */
    private Long timestamp;

	public String getPatientId() {
		// TODO Auto-generated method stub
		return patientId;
	}
}
