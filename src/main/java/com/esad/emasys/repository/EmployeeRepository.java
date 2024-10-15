package com.esad.emasys.repository;

import com.esad.emasys.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    //get employee using userId
    Optional<Employee> findByUserId(Integer userId); // Custom query method
}
