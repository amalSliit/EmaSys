package com.esad.emasys.controller;

import com.esad.emasys.facade.EmployeeFacade;
import com.esad.emasys.model.Leave;
import com.esad.emasys.model.LeaveRequest;
import com.esad.emasys.model.LeaveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final EmployeeFacade empFacade;

    @Autowired
    public LeaveController(EmployeeFacade employeeFacade) {
        this.empFacade = employeeFacade;
    }

    @PostMapping("/request")
    public ResponseEntity<LeaveResponse> requestLeave(@RequestBody LeaveRequest request) {
        try {
            return ResponseEntity.ok(empFacade.requestLeave(request));
        } catch (Exception e) {
            LeaveResponse response = new LeaveResponse("Failed", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/pending/{employeeId}")
    public ResponseEntity<Leave> getPendingLeaveRequests(@PathVariable int employeeId) {
        Leave pendingLeave = empFacade.getPendingLeave(employeeId);
        return ResponseEntity.ok(pendingLeave);
    }

    @GetMapping("/status/{employeeId}")
    public ResponseEntity<Leave> getRequestStatus(@PathVariable int employeeId) {
        Leave pendingLeave = empFacade.getLeaveStatus(employeeId);
        return ResponseEntity.ok(pendingLeave);
    }

}
