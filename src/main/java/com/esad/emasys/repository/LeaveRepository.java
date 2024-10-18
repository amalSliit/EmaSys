package com.esad.emasys.repository;

import com.esad.emasys.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    Leave findPendingLeaveByEmployeeId(Integer employeeId);
}
