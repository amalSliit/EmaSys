package com.esad.emasys.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServies {
    @Autowired private EmployeeRepo empRepo;

    public List<Employee> getAllEmployees() {
        return (List<Employee>) empRepo.findAll();
    }

    public void save(Employee emp) {
        empRepo.save(emp);
    }
}
