package com.esad.emasys.controller;

import com.esad.emasys.model.AttendanceResponse;
import com.esad.emasys.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    //private final JwtUtil jwtUtil;

    public AttendanceController(AttendanceService attenService) {
        this.attendanceService = attenService;
        //this.jwtUtil = new JwtUtil();
    }

    // Remove "Bearer " prefix if it exists in token
    private String getFilterToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            return token;
        }
    }

    @PostMapping("/checkin")
    public ResponseEntity<AttendanceResponse> checkIn(@RequestHeader("Authorization") String authToken) {

        String token = getFilterToken(authToken);

        /*if (jwtUtil.validateToken(token)) {
            // Get employee ID from token
            int empId = jwtUtil.getEmployeeId(token);

            //save checkIn
            LocalDateTime checkInTime = attendanceService.checkIn(empId);
            return ResponseEntity.ok(new AttendanceResponse("Checked in successfully", checkInTime));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AttendanceResponse("Unauthorized Access. Please re-login.", null));
        }*/

        // Get employee ID from token
        int empId = 40;

        //save checkIn
        LocalDateTime checkInTime = attendanceService.checkIn(empId);
        return ResponseEntity.ok(new AttendanceResponse("Checked in successfully", checkInTime));
    }

    @PostMapping("/checkout")
    public ResponseEntity<AttendanceResponse> checkOut(@RequestHeader("Authorization") String authToken) {

        String token = getFilterToken(authToken);

        /*if (jwtUtil.validateToken(token)) {
            // Get employee ID from token
            int empId = jwtUtil.getEmployeeId(token);

            //save checkOut
            LocalDateTime checkOutTime = attendanceService.checkOut(empId);
            return ResponseEntity.ok(new AttendanceResponse("Checked out successfully", checkOutTime));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AttendanceResponse("Unauthorized Access. Please re-login.", null));
        }*/

        // Get employee ID from token
        int empId = 40;

        //save checkOut
        LocalDateTime checkOutTime = attendanceService.checkOut(empId);
        return ResponseEntity.ok(new AttendanceResponse("Checked out successfully", checkOutTime));
    }
}
