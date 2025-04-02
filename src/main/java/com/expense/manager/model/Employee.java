package com.expense.manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "mobile_number", length = 15, nullable = false)
    private String mobileNumber;


    @Column(unique = true, nullable = false)
    private String employeeCode;  // User-defined unique ID
    
    @Column(nullable = false)
    private String localAddress;
    
    @Column(nullable = false)
    private String permanentAddress;
    
    @Column(nullable = false, unique = true, length = 12)
    private String aadharNumber;
    
    @Column(nullable = false)
    private int age;
    
    @Column(nullable = false)
    private boolean active = true; // Default active status

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkType work; // Work type field

    public WorkType getWork() {
        return work;
    }

    public void setWork(WorkType work) {
        this.work = work;
    }


    
    // Getters and Setters
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
