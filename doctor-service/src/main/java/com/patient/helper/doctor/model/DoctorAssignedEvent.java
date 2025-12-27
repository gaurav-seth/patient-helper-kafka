package com.patient.helper.doctor.model;

/**
 * DoctorAssignedEvent
 *
 * This event is published by doctor-service after
 * assigning a doctor to a registered patient.
 *
 * It is sent to the Kafka topic "doctor.assigned".
 */

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

    public DoctorAssignedEvent() {
    	
    }
    
    public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public DoctorAssignedEvent(String patientId, String doctorId, String doctorName, String specialization, long timeMillis) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.timestamp = timeMillis;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	
}
