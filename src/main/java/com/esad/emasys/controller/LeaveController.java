package com.esad.emasys.controller;

import com.esad.emasys.impl.EmployeeServiceImpl;
import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.model.LeaveRequest;
import com.esad.emasys.services.EmployeeService;
import com.esad.emasys.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/request")
    public ResponseEntity<Leave> requestLeave(@RequestBody LeaveRequest request) {

        Employee employee = employeeService.getEmployee(request.getEmployeeId());

        // Convert String dates to LocalDate
        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        // Call the service to process the leave request
        Leave leave = leaveService.requestLeave(employee, startDate, endDate, request.getReason(), request.getType());

        return ResponseEntity.ok(leave);
    }
}
