package com.esad.emasys.services;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository; // Assuming you've created a LeaveRepository interface

    public Leave requestLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {
        Leave leave = new Leave(employee, startDate, endDate, LocalDate.now(), reason, type);
        return leaveRepository.save(leave); // Save the leave request to the database
    }
}
