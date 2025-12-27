package com.patient.helper.doctor.policy;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PolicyResolver {

    private final Map<String, DoctorAssignmentPolicy> policies;

    public PolicyResolver(Map<String, DoctorAssignmentPolicy> policies) {
        this.policies = policies;
    }

    public DoctorAssignmentPolicy resolve(String version) {
        return policies.getOrDefault(version, policies.get("v1"));
    }
}
