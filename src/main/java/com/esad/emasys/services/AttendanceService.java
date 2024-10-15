package com.esad.emasys.services;

import com.esad.emasys.impl.EmployeeServiceImpl;
import com.esad.emasys.model.Attendance;
import com.esad.emasys.model.Employee;
import com.esad.emasys.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeServiceImpl empService;

    public LocalDateTime checkIn(Integer empId) {
        Employee emp = empService.getEmployee(empId);

        // Check if the user has already checked in and hasn't checked out yet
        if (attendanceRepository.findByEmployeeAndCheckOutTimeIsNull(emp).isPresent()) {
            throw new IllegalStateException("User has already checked in but not checked out yet");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployee(emp);
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setAttendanceDate(LocalDateTime.now());
        attendanceRepository.save(attendance);

        return attendance.getCheckInTime();
    }

    public LocalDateTime checkOut(Integer empId) {
        Employee emp = empService.getEmployee(empId);

        Attendance attendance = attendanceRepository.findByEmployeeAndCheckOutTimeIsNull(emp)
                .orElseThrow(() -> new IllegalStateException("User has not checked in yet"));

        LocalDateTime checkOutTime = LocalDateTime.now();
        attendance.setCheckOutTime(checkOutTime);
        attendance.setStatus(Attendance.Status.CHECK_OUT);

        // Calculate the total worked hours
        LocalDateTime checkInTime = attendance.getCheckInTime();
        Duration duration = Duration.between(checkInTime, checkOutTime);
        float totalHours = duration.toMinutes() / 60.0f;  // Convert minutes to hours

        // Set the total hours worked
        attendance.setTotalHours(totalHours);
        attendanceRepository.save(attendance);

        return attendance.getCheckOutTime();
    }
}
