package com.patient.helper.doctor.policy;

import org.springframework.stereotype.Component;
import com.patient.helper.events.PatientRegisteredEvent;
import com.patient.helper.doctor.model.DoctorAssignedEvent;

@Component("v3")
public class PolicyV3 implements DoctorAssignmentPolicy {

    @Override
    public DoctorAssignedEvent assign(PatientRegisteredEvent e) {
    	return new DoctorAssignedEvent(
                e.getPatientId().toString(),
                "D-103",
                "Emergency Care",
                "Emergency",
                System.currentTimeMillis()
            );
    }
}
