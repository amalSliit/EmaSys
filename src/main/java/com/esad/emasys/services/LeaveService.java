package com.esad.emasys.services;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;

import java.time.LocalDate;


public interface LeaveService {
    Integer requestingLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type);

    Leave getPendingLeave(int employeeId);

    Leave getStatus(int employeeId);
}
