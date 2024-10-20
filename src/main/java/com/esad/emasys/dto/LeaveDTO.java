package com.esad.emasys.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
* Data Transfer Objects Design Pattern
* Using LeaveDTO simplifies what gets exposed to clients and abstracts away internal details.
* This approach ensures security and flexibility when managing and extending the Employee Attendance System.
* */
public class LeaveDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime requestedDate;
    private String reason;
    private String leaveType;
    private String leaveStatus;

    public LeaveDTO() {
    }

    public LeaveDTO(Long id, LocalDate startDate, LocalDate endDate,
                    LocalDateTime requestedDate, String reason, String leaveType, String leaveStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestedDate = requestedDate;
        this.reason = reason;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
