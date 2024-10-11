package com.esad.emasys.employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee emp);

    public Employee getEmployee(Integer id);

    public void deleteEmployee(Integer id);
}
