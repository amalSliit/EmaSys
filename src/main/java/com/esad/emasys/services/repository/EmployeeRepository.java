package com.esad.emasys.services.repository;

import com.esad.emasys.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
