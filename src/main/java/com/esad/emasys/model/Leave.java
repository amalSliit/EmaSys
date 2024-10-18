package com.esad.emasys.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee_leave") // Renamed table to "leave"
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Reference to Employee

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false, name = "requested_date")
    private LocalDate requestedDate;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING; // Default to PENDING

    @ManyToOne
    @JoinColumn(name = "manage_by")
    private Employee manageBy; // Admin or Manager employee

    // Default constructor
    public Leave() {
    }

    // Parameterized constructor
    public Leave(Employee employee, LocalDate startDate, LocalDate endDate, LocalDate requestedDate,
                 String reason, LeaveType type) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestedDate = requestedDate;
        this.reason = reason;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public Employee getManageBy() {
        return manageBy;
    }

    public void setManageBy(Employee manageBy) {
        this.manageBy = manageBy;
    }

    // Enum for Leave Type
    public enum LeaveType {
        SICK,
        ANNUAL,
        OTHER
    }

    // Enum for Leave Status
    public enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
