package com.ust.financeanalyzer.dto;


public class Employeedto {
    private String id;
    private String name;
    private String contact;
    private String email;
    private String projectid;
    private Double salary;
    private Double tax;

    public Employeedto(String id, String name, String contact, String email, String projectid, Double salary, Double tax) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.projectid = projectid;
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

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
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
