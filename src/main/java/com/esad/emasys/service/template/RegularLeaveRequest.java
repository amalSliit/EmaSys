package com.esad.emasys.service.template;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import com.esad.emasys.repository.LeaveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegularLeaveRequest extends LeaveRequestTemplate {

    private final LeaveRepository leaveRepository;

    public RegularLeaveRequest(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
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
    protected boolean checkLeaveBalance(Employee employee, Leave.LeaveType type) {
        // Logic to check leave balance for annual leave
        /*int availableLeaveDays = employee.getAnnualLeaveBalance();  // Example method to get annual leave balance
        int requestedDays = (int) ChronoUnit.DAYS.between(employee.s(), employee.getEndDate());

        if (availableLeaveDays < requestedDays) {
            System.out.println("Insufficient annual leave balance.");
            return false;
        }*/
        return true;
    }

    @Override
    protected void saveLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {
        Leave leave = new Leave(employee, startDate, endDate, LocalDateTime.now(), reason, type);
        leaveRepository.save(leave);
    }
}
