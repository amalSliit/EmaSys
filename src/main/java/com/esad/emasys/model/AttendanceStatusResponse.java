package com.esad.emasys.model;

import java.time.LocalDateTime;

public class AttendanceStatusResponse {
    private Attendance.Status status;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private LocalDateTime attendanceDate;
    private Float totalHours;

    // Constructor
    public AttendanceStatusResponse(Attendance.Status status, LocalDateTime checkInTime, LocalDateTime checkOutTime, LocalDateTime attendanceDate, Float totalHours) {
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceDate = attendanceDate;
        this.totalHours = totalHours;
    }

    // Getters and Setters
    public Attendance.Status getStatus() {
        return status;
    }

    public void setStatus(Attendance.Status status) {
        this.status = status;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDateTime getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDateTime attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Float totalHours) {
        this.totalHours = totalHours;
    }
}

