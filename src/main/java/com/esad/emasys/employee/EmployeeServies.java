package com.esad.emasys.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServies {
    @Autowired private EmployeeRepo empRepo;

    public List<Employee> getAllEmployees() {
        return (List<Employee>) empRepo.findAll();
    }

    public void saveEmployee(Employee emp) {
        empRepo.save(emp);
    }

    public Employee getEmployee(Integer id) {
        Optional<Employee> emp = empRepo.findById(id);
        return emp.orElse(null);
    }
}
