package com.esad.emasys.service.impl;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.repository.LeaveBalanceRepository;
import com.esad.emasys.repository.LeaveRepository;
import com.esad.emasys.service.interfaces.LeaveService;
import com.esad.emasys.service.template.LeaveRequestTemplate;
import com.esad.emasys.service.template.RegularLeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveBalanceRepository lbRepo;

    @Override
    public Integer requestingLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {

        LeaveRequestTemplate leaveReqPros = new RegularLeaveRequest(leaveRepository, lbRepo);
        return leaveReqPros.requestingLeave(employee, startDate, endDate, reason, type);


        // Check for existing pending leave requests for the employee
        /*Leave existingLeave = leaveRepository.getPendingLeave(employee.getId(), Leave.LeaveStatus.PENDING);

        if (existingLeave != null) {
            return HttpStatus.FOUND.value();
        } else {
            // Create a new leave request if there are no existing pending requests
            Leave leave = new Leave(employee, startDate, endDate, LocalDateTime.now(), reason, type);
            leaveRepository.save(leave);
            return HttpStatus.OK.value();
        }*/
    }

    @Override
    public Leave getPendingLeave(int employeeId) {
        return leaveRepository.getPendingLeave(employeeId, Leave.LeaveStatus.PENDING);
    }

    @Override
    public Leave getStatus(int employeeId) {
        return leaveRepository.getLastRequestStatus(employeeId);
    }
}
