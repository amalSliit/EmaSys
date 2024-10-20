package com.esad.emasys.model;

public class LeaveRequest {
    private Integer employeeId;
    private String startDate;
    private String endDate;
    private String reason;
    private Leave.LeaveType type;

    public LeaveRequest(Integer employeeId, String startDate, String endDate, String reason, Leave.LeaveType type) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.type = type;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Leave.LeaveType getType() {
        return type;
    }

    public void setType(Leave.LeaveType type) {
        this.type = type;
    }
}