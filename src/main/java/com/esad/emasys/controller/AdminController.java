package com.esad.emasys.controller;

import com.esad.emasys.model.Leave;
import com.esad.emasys.service.interfaces.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping("/leave-requests")
    public ResponseEntity<List<Leave>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/leave-requests/{id}")
    public ResponseEntity<Leave> getLeaveRequestById(@PathVariable int id) {
        Optional<Leave> leaveRequest = leaveRequestService.getLeaveRequestByID(id);
        return leaveRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/leave-requests/{id}/accept")
    public ResponseEntity<String> acceptLeaveRequest(@PathVariable int id) {
        Optional<Leave> updatedLeaveRequest = leaveRequestService.acceptLeaveRequest(id);
        return updatedLeaveRequest
                .map(leaveRequest -> ResponseEntity.ok("Leave Request Accepted!"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Leave request with ID " + id + " not found!"));
    }

    @PatchMapping("/leave-requests/{id}/decline")
    public ResponseEntity<String> declineLeaveRequest(@PathVariable int id) {
        Optional<Leave> updatedLeaveRequest = leaveRequestService.declineLeaveRequest(id);
        return updatedLeaveRequest
                .map(leaveRequest -> ResponseEntity.ok("Leave Request Declined!"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Leave request with ID " + id + " not found!"));
    }


}
