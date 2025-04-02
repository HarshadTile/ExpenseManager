package com.expense.manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate date; // Attendance Date

    @Enumerated(EnumType.STRING)
    private Status status;  // PRESENT or ABSENT

    public enum Status {
        PRESENT, ABSENT
    }

    // Default Constructor
    public Attendance() {
    }

    // Parameterized Constructor
    public Attendance(Employee employee, LocalDate date, Status status) {
        this.employee = employee;
        this.date = date;
        this.status = status;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}