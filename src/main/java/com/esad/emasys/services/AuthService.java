package com.esad.emasys.services;

import com.esad.emasys.impl.EmployeeServiceImpl;
import com.esad.emasys.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private EmployeeServiceImpl empService;

    public boolean isLogin(String email) {
        Employee emp = empService.getEmployee(email);
        if (emp != null) {
            return emp.getEmail().equals(email);
        } else {
            return false;
        }
    }

    public Employee getEmployee(String email) {
        return empService.getEmployee(email);
    }
}
