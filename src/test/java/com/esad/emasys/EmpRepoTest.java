package com.esad.emasys;

import com.esad.emasys.model.Employee;
import com.esad.emasys.services.repository.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmpRepoTest {
    @Autowired private EmployeeRepo empRepo;

    @Test
    public void SaveEmployeeTest() {
        Employee newEmp = new Employee();
        newEmp.setFirstName("John");
        newEmp.setLastName("Smith");
        newEmp.setEmail("john.smith@gmail.com");
        newEmp.setPhotoUrl("https://www.google.com/photo?aslksdksdk");
        newEmp.setDepartmentId(201);
        newEmp.setPositionId(101);

        Employee savedEmp = empRepo.save(newEmp);

        Assertions.assertNotNull(savedEmp);
        Assertions.assertNotNull(savedEmp.getId());
    }
}
