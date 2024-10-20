package com.esad.emasys.services;

import com.esad.emasys.model.LeaveRequest;
import com.esad.emasys.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {
    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> getLeaveRequestByID(int id) {
        return leaveRequestRepository.findById(id);
    }

    public Optional<LeaveRequest> acceptLeaveRequest(int id) {
        return leaveRequestRepository.findById(id).map(leaveRequest -> {
            leaveRequest.setStatus(LeaveRequest.Status.APPROVED);
            return leaveRequestRepository.save(leaveRequest);
        });
    }

    public Optional<LeaveRequest> declineLeaveRequest(int id) {
        return leaveRequestRepository.findById(id).map(leaveRequest -> {
            leaveRequest.setStatus(LeaveRequest.Status.REJECTED);
            return leaveRequestRepository.save(leaveRequest);
        });
    }

}
