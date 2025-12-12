package com.patient.helper.reception.controller;

/**
 * PatientRequest
 *
 * This class represents the incoming JSON request body for
 * registering a patient. It contains only the data fields that
 * are provided by the client.
 *
 * Fields:
 * - name
 * - age
 * - phone
 */
public class PatientRequest {

    private String name;
    private Integer age;
    private String phone;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
