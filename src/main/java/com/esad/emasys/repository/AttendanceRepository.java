package com.esad.emasys.repository;

import com.esad.emasys.model.Attendance;
import com.esad.emasys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByEmployeeAndCheckOutTimeIsNull(Employee employee);
}

