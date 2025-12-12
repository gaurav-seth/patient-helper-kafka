package com.patient.helper.reception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisteredEvent {

    private String patientId;
    private String name;
    private Integer age;
    private String phone;
    private Long timestamp;
    
    public PatientRegisteredEvent(String patientId2, String name2, Integer age2, String phone2, long timestamp2) {
		this.patientId = patientId2;
		this.name = name2;
		this.age = age2;
		this.phone = phone2;
		this.timestamp = timestamp2;
	}

	public String getPatientId() {
        return patientId;
    }

}
