package com.ust.financeanalyzer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Employeedto {
    private String id;
    private String name;
    private String contact;
    private String email;
    @JsonProperty("projectId")
    private String projectId;
    private Double salary;
    private Double tax;

    public Employeedto(String id, String name, String contact, String email, String projectId, Double salary, Double tax) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.projectId = projectId;
        this.salary = salary;
        this.tax = tax;
    }

    public Employeedto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectId() { // Correct method name
        return projectId;
    }

    public void setProjectId(String projectId) { // Correct method name
        this.projectId = projectId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
