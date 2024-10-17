package com.esad.emasys.repository;

import com.esad.emasys.model.Attendance;
import com.esad.emasys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByEmployeeAndCheckOutTimeIsNull(Employee employee);

    // Find the latest CHECK_IN with totalHours = 0 for a specific employee
    Optional<Attendance> findFirstByEmployeeIdAndStatusOrderByCheckInTimeDesc(int employeeId, Attendance.Status status);

    // Find the latest CHECK_OUT for a specific employee
    Optional<Attendance> findFirstByEmployeeIdAndStatusOrderByCheckOutTimeDesc(int employeeId, Attendance.Status status);

}

