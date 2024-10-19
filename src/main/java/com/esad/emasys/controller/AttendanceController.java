package com.esad.emasys.controller;

import com.esad.emasys.facade.EmployeeFacade;
import com.esad.emasys.model.AttendanceRequest;
import com.esad.emasys.model.AttendanceResponse;
import com.esad.emasys.model.AttendanceStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final EmployeeFacade empFacade;

    @Autowired
    public AttendanceController(EmployeeFacade employeeFacade) {
        this.empFacade = employeeFacade;
    }

    @PostMapping("/checkin")
    public ResponseEntity<AttendanceResponse> checkIn(@RequestBody AttendanceRequest attendanceRequest) {
        // Save check-in time
        LocalDateTime checkInTime = empFacade.checkIn(attendanceRequest.getEmpId());
        return ResponseEntity.ok(new AttendanceResponse("Checked in successfully", checkInTime));
    }

    @PostMapping("/checkout")
    public ResponseEntity<AttendanceResponse> checkOut(@RequestBody AttendanceRequest attendanceRequest) {
        // Save check-out time
        LocalDateTime checkOutTime = empFacade.checkOut(attendanceRequest.getEmpId());
        return ResponseEntity.ok(new AttendanceResponse("Checked out successfully", checkOutTime));
    }

    @PostMapping("/status")
    public ResponseEntity<AttendanceStatusResponse> getAttendanceStatus(@RequestBody AttendanceRequest attendanceRequest) {
        AttendanceStatusResponse attendanceStatusDto = empFacade.getAttendanceStatus(attendanceRequest.getEmpId());
        return ResponseEntity.ok(attendanceStatusDto);
    }
}
