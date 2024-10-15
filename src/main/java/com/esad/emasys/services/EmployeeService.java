package com.esad.emasys.services;

import com.esad.emasys.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee emp);

    public Employee getEmployee(Integer id);

    public Employee getEmployeeUserId(Integer user_id);

    public void deleteEmployee(Integer id);

}
