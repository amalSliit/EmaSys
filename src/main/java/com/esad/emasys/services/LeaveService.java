package com.esad.emasys.services;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public Integer requestingLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {

        // Check for existing pending leave requests for the employee
        Leave existingLeave = leaveRepository.getPendingLeave(employee.getId(), Leave.LeaveStatus.PENDING);

        if (existingLeave != null) {
            return HttpStatus.FOUND.value();
        } else {
            // Create a new leave request if there are no existing pending requests
            Leave leave = new Leave(employee, startDate, endDate, LocalDateTime.now(), reason, type);
            leaveRepository.save(leave);
            return HttpStatus.OK.value();
        }
    }

    public Leave getPendingLeave(int employeeId) {
        return leaveRepository.getPendingLeave(employeeId, Leave.LeaveStatus.PENDING);
    }

    public Leave getStatus(int employeeId) {
        return leaveRepository.getLastRequestStatus(employeeId);
    }
}
