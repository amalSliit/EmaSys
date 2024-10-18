package com.esad.emasys.repository;

import com.esad.emasys.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    @Query("SELECT l FROM Leave l WHERE l.employee.id = :employeeId AND l.status = :leaveStatus")
    Leave getPendingLeave(
            @Param("employeeId") Integer employeeId,
            @Param("leaveStatus") Leave.LeaveStatus leaveStatus);

}
