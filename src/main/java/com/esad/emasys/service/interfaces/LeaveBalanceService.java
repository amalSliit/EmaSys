package com.esad.emasys.service.interfaces;

import com.esad.emasys.model.LeaveBalance;
import com.esad.emasys.repository.LeaveBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    public LeaveBalanceService(LeaveBalanceRepository leaveBalanceRepository) {
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    public LeaveBalance getLeaveBalance(Integer employeeId) {
        return leaveBalanceRepository.findByEmployeeId(employeeId);
    }

    public LeaveBalance saveLeaveBalance(LeaveBalance leaveBalance) {
        return leaveBalanceRepository.save(leaveBalance);
    }
}
