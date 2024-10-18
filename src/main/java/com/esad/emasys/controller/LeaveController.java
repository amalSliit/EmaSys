package com.esad.emasys.controller;

import com.esad.emasys.impl.EmployeeServiceImpl;
import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.model.LeaveRequest;
import com.esad.emasys.model.LeaveResponse;
import com.esad.emasys.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/request")
    public ResponseEntity<LeaveResponse> requestLeave(@RequestBody LeaveRequest request) {

        try {
            Employee employee = employeeService.getEmployee(request.getEmployeeId());

            // Convert String dates to LocalDate
            LocalDate startDate = LocalDate.parse(request.getStartDate());
            LocalDate endDate = LocalDate.parse(request.getEndDate());

            // Call the service to process the leave request
            Integer status = leaveService.requestingLeave(employee, startDate, endDate, request.getReason(), request.getType());

            LeaveResponse response;
            if (status == HttpStatus.FOUND.value()) {
                response = new LeaveResponse("Failed", "An employee can only have one pending leave request at a time.");
            } else {
                response = new LeaveResponse("Success", null);
            }
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Return failure message in JSON format if something goes wrong
            LeaveResponse response = new LeaveResponse("Failed", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/pending/{employeeId}")
    public ResponseEntity<Leave> getPendingLeaveRequests(@PathVariable int employeeId) {
        Leave pendingLeave = leaveService.getPendingLeave(employeeId);
        return ResponseEntity.ok(pendingLeave);
    }

}
