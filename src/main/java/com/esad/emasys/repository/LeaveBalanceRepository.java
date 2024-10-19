package com.esad.emasys.repository;

import com.esad.emasys.model.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    LeaveBalance findByEmployeeId(Integer employeeId);
}
