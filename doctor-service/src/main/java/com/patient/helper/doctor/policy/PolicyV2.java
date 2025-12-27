package com.patient.helper.doctor.policy;

import org.springframework.stereotype.Component;
import com.patient.helper.events.PatientRegisteredEvent;
import com.patient.helper.doctor.model.DoctorAssignedEvent;

@Component("v2")
public class PolicyV2 implements DoctorAssignmentPolicy {

    @Override
    public DoctorAssignedEvent assign(PatientRegisteredEvent e) {
    	return new DoctorAssignedEvent(
                e.getPatientId().toString(),
                "D-102",
                "Dr. General",
                "General Physician",
                System.currentTimeMillis()
            );
    }
}
