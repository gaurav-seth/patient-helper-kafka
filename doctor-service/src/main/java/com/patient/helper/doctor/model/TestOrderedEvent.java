package com.patient.helper.doctor.model;

public class TestOrderedEvent {

    private String patientId;
    private String testName;
    private long orderedAt;

    public TestOrderedEvent(String patientId, String testName, long orderedAt) {
        this.patientId = patientId;
        this.testName = testName;
        this.orderedAt = orderedAt;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getTestName() {
        return testName;
    }

    public long getOrderedAt() {
        return orderedAt;
    }
}
