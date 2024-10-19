package com.esad.emasys.service.template;

import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Leave;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public abstract class LeaveRequestTemplate {

    // Template method defining the leave request process
    public final Integer requestingLeave(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type) {

        // Validate request
        if (!validateLeaveRequest(employee, startDate, endDate)) {
            return HttpStatus.BAD_REQUEST.value();
        }

        // Check for existing pending leave
        Leave existingLeave = checkExistingPendingLeave(employee);
        if (existingLeave != null) {
            return HttpStatus.FOUND.value();
        }

        // Check leave balance availability
        if (!checkLeaveBalance(employee, type, startDate, endDate)) {
            return HttpStatus.PRECONDITION_FAILED.value(); // 412 Precondition Failed if balance is insufficient
        }

        // Save the leave request
        saveLeaveRequest(employee, startDate, endDate, reason, type);

        // Notify the employee
        notifyEmployee(employee);

        return HttpStatus.OK.value();
    }


    protected abstract boolean validateLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate);

    protected abstract Leave checkExistingPendingLeave(Employee employee);

    protected abstract boolean checkLeaveBalance(Employee employee, Leave.LeaveType type, LocalDate startDate, LocalDate endDate);

    protected abstract void saveLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate, String reason, Leave.LeaveType type);

    protected void notifyEmployee(Employee employee) {
        System.out.println("Send email to " + employee.getEmail());
    }
}
