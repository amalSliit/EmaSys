package com.esad.emasys.controller;

import com.esad.emasys.model.*;
import com.esad.emasys.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/checkin")
    public ResponseEntity<AttendanceResponse> checkIn(@RequestBody AttendanceRequest attendanceRequest) {
        // Save check-in time
        LocalDateTime checkInTime = attendanceService.checkIn(attendanceRequest.getEmpId());
        return ResponseEntity.ok(new AttendanceResponse("Checked in successfully", checkInTime));
    }

    @PostMapping("/checkout")
    public ResponseEntity<AttendanceResponse> checkOut(@RequestBody AttendanceRequest attendanceRequest) {
        // Save check-out time
        LocalDateTime checkOutTime = attendanceService.checkOut(attendanceRequest.getEmpId());
        return ResponseEntity.ok(new AttendanceResponse("Checked out successfully", checkOutTime));
    }

    @GetMapping("/status")
    public ResponseEntity<AttendanceStatusResponse> getAttendanceStatus(@RequestBody AttendanceRequest attendanceRequest) {
        AttendanceStatusResponse attendanceStatusDto = attendanceService.getStatus(attendanceRequest.getEmpId());
        return ResponseEntity.ok(attendanceStatusDto);
    }
}
