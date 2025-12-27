package com.patient.helper.doctor.policy;

import org.springframework.stereotype.Component;
import com.patient.helper.events.PatientRegisteredEvent;
import com.patient.helper.doctor.model.DoctorAssignedEvent;

@Component("v1")
public class PolicyV1 implements DoctorAssignmentPolicy {

    @Override
    public DoctorAssignedEvent assign(PatientRegisteredEvent e) {
        if (e.getAge() >= 60) {
            return new DoctorAssignedEvent(
                e.getPatientId().toString(),
                "D-101",
                "Dr. Senior",
                "Senior Care",
                System.currentTimeMillis()
            );
        }

        return new DoctorAssignedEvent(
            e.getPatientId().toString(),
            "D-102",
            "Dr. General",
            "General Physician",
            System.currentTimeMillis()
        );
    }
}
