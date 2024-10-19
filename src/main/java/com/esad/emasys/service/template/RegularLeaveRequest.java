package com.esad.emasys.service.template;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.model.LeaveBalance;
import com.esad.emasys.repository.LeaveBalanceRepository;
import com.esad.emasys.repository.LeaveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RegularLeaveRequest extends LeaveRequestTemplate {

    private final LeaveRepository leaveRepository;
    private final LeaveBalanceRepository lbRepository;

    public RegularLeaveRequest(LeaveRepository leaveRepository, LeaveBalanceRepository lbRepository) {
        this.leaveRepository = leaveRepository;
        this.lbRepository = lbRepository;
    }

    @Override
    protected boolean validateLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        return !startDate.isBefore(LocalDate.now()) || !endDate.isAfter(LocalDate.now()) || employee == null;
    }

    @Override
    protected Leave checkExistingPendingLeave(Employee employee) {
        return leaveRepository.getPendingLeave(employee.getId(), Leave.LeaveStatus.PENDING);
    }

    @Override
    protected boolean checkLeaveBalance(Employee employee, Leave.LeaveType type, LocalDate startDate, LocalDate endDate) {

        LeaveBalance lb = lbRepository.findByEmployeeId(employee.getId());

        // No leave balance record exists
        if (lb == null) {
            return false;
        }


        int requestedDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1; // Include end date
        int availableLeaveDays;


        // Get available leave days based on the leave type
        switch (type) {
            case ANNUAL:
                availableLeaveDays = lb.getAnnualLeave();
                break;
            case SICK:
                availableLeaveDays = lb.getSickLeave();
                break;
            case CASUAL:
                availableLeaveDays = lb.getCasualLeave();
                break;
            case MATERNITY:
                availableLeaveDays = lb.getMaternityLeave();
                break;
            case PATERNITY:
                availableLeaveDays = lb.getPaternityLeave();
                break;
            case COMPENSATORY:
                availableLeaveDays = lb.getCompensatoryLeave();
                break;
            default:
                return false;
        }

        // Check if the requested leave days exceed available leave days
        return availableLeaveDays >= requestedDays;
    }

    @Override
    protected void saveLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {
        Leave leave = new Leave(employee, startDate, endDate, LocalDateTime.now(), reason, type);
        leaveRepository.save(leave);
    }
}
