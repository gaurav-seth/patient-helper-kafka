package com.patient.helper.doctor.policy;

import com.patient.helper.doctor.model.DoctorAssignedEvent;
import com.patient.helper.events.PatientRegisteredEvent;

public interface DoctorAssignmentPolicy {
	DoctorAssignedEvent assign(PatientRegisteredEvent event);
}
