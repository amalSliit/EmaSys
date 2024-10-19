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
