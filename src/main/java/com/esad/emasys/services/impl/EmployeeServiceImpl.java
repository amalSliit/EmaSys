package com.esad.emasys.services.impl;

import com.esad.emasys.model.Employee;
import com.esad.emasys.services.EmployeeService;
import com.esad.emasys.services.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) empRepo.findAll();
    }

    @Override
    public void saveEmployee(Employee emp) {
        empRepo.save(emp);
    }

    @Override
    public Employee getEmployee(Integer id) {
        Optional<Employee> emp = empRepo.findById(id);
        return emp.orElse(null);
    }

    @Override
    public void deleteEmployee(Integer id) {
        empRepo.deleteById(id);
    }
}
