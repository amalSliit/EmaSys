package com.esad.emasys.services;

import com.esad.emasys.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee emp);

    Employee getEmployee(Integer id);

    Employee getEmployee(String email);

    void deleteEmployee(Integer id);

}
