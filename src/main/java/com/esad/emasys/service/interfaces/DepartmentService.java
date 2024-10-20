package com.esad.emasys.service.interfaces;

import com.esad.emasys.model.Department;
import com.esad.emasys.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }
}
