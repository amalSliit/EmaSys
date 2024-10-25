package com.esad.emasys.service.interfaces;

import com.esad.emasys.model.Leave;
import com.esad.emasys.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {
    @Autowired
    LeaveRepository leaveRepository;

    public List<Leave> getAllLeaveRequests() {
        return leaveRepository.findAll();
    }

    public Optional<Leave> getLeaveRequestByID(int id) {
        return leaveRepository.findById(id);
    }

    public Optional<Leave> acceptLeaveRequest(int id) {
        return leaveRepository.findById(id).map(leave -> {
            leave.setStatus(Leave.LeaveStatus.APPROVED);
            return leaveRepository.save(leave);
        });
    }

    public Optional<Leave> declineLeaveRequest(int id) {
        return leaveRepository.findById(id).map(leave -> {
            leave.setStatus(Leave.LeaveStatus.REJECTED);
            return leaveRepository.save(leave);
        });
    }

}
