package com.patient.helper.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DoctorAssignedEvent
 *
 * This event is published by doctor-service after
 * assigning a doctor to a registered patient.
 *
 * It is sent to the Kafka topic "doctor.assigned".
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAssignedEvent {

    /**
     * Patient identifier
     */
    private String patientId;

    /**
     * Assigned doctor identifier
     */
    private String doctorId;

    /**
     * Doctor full name
     */
    private String doctorName;

    /**
     * Doctor specialization
     */
    private String specialization;

    /**
     * Event creation timestamp (epoch millis)
     */
    private Long timestamp;

	public DoctorAssignedEvent(String patientId, String doctorId, String doctorName, String specialization, long timeMillis) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.timestamp = timeMillis;
	}

	public String getPatientId() {
		// TODO Auto-generated method stub
		return patientId;
	}
}
