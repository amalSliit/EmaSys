package com.esad.emasys.service.interfaces;

import com.esad.emasys.model.AttendanceStatusResponse;

import java.time.LocalDateTime;

public interface AttendanceService {
    LocalDateTime checkIn(Integer empId);

    LocalDateTime checkOut(Integer empId);

    AttendanceStatusResponse getStatus(Integer empId);
}
