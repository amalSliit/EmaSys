package com.esad.emasys.facade;

import com.esad.emasys.service.impl.AttendanceServiceImpl;
import com.esad.emasys.service.impl.EmployeeServiceImpl;
import com.esad.emasys.service.impl.LeaveServiceImpl;
import com.esad.emasys.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class EmployeeFacade {

    private final AttendanceServiceImpl attendanceService;
    private final LeaveServiceImpl leaveService;
    private final EmployeeServiceImpl employeeService;

    public EmployeeFacade(AttendanceServiceImpl attendanceService, LeaveServiceImpl leaveService, EmployeeServiceImpl employeeService) {
        this.attendanceService = attendanceService;
        this.leaveService = leaveService;
        this.employeeService = employeeService;
    }

    //Attendance
    public LocalDateTime checkIn(Integer employeeId) {
        return attendanceService.checkIn(employeeId);
    }

    public LocalDateTime checkOut(Integer employeeId) {
        return attendanceService.checkOut(employeeId);
    }

    public AttendanceStatusResponse getAttendanceStatus(Integer employeeId) {
        return attendanceService.getStatus(employeeId);
    }

    //Leave
    public LeaveResponse requestLeave(LeaveRequest request) throws Exception {
        Employee employee = employeeService.getEmployee(request.getEmployeeId());
        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        Integer status = leaveService.requestingLeave(employee, startDate, endDate, request.getReason(), request.getType());

        if (status == 302) {
            return new LeaveResponse("Failed", "An employee can only have one pending leave request at a time.");
        } else {
            return new LeaveResponse("Success", null);
        }
    }

    public Leave getPendingLeave(int employeeId) {
        return leaveService.getPendingLeave(employeeId);
    }

    public Leave getLeaveStatus(int employeeId) {
        return leaveService.getStatus(employeeId);
    }
}