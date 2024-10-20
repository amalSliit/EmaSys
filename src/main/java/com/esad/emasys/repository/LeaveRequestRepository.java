package com.esad.emasys.repository;

import com.esad.emasys.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findAllByOrderByRequestDateDesc(); //optional: Order by request Date
}
