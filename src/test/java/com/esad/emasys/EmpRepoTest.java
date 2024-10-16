package com.esad.emasys;

import com.esad.emasys.model.Employee;
import com.esad.emasys.repository.EmployeeRepository;
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
    @Autowired private EmployeeRepository empRepo;

    @Test
    public void SaveEmployeeTest() {
        Employee newEmp = new Employee();
        newEmp.setFirstName("John");
        newEmp.setLastName("Smith");
        newEmp.setPhotoUrl("https://www.google.com/photo?aslksdksdk");
        newEmp.setDepartmentId(1);
        newEmp.setPositionId(1);

        Employee savedEmp = empRepo.save(newEmp);

        Assertions.assertNotNull(savedEmp);
        Assertions.assertNotNull(savedEmp.getId());
    }
}
