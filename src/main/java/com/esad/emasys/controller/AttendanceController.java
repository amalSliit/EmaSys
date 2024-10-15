package com.esad.emasys.controller;

import com.esad.emasys.model.Attendance;
import com.esad.emasys.model.AttendanceResponse;
import com.esad.emasys.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/checkin/{empId}")
    public ResponseEntity<AttendanceResponse> checkIn(@PathVariable Integer empId) {
        LocalDateTime checkInTime = attendanceService.checkIn(empId);
        return ResponseEntity.ok(new AttendanceResponse("Checked in successfully", checkInTime));
    }

    @PostMapping("/checkout/{empId}")
    public ResponseEntity<AttendanceResponse> checkOut(@PathVariable Integer empId) {
        LocalDateTime checkOutTime = attendanceService.checkOut(empId);
        return ResponseEntity.ok(new AttendanceResponse("Checked out successfully", checkOutTime));
    }
}
