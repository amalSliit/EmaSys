package com.esad.emasys.controller;

import com.esad.emasys.model.Department;
import com.esad.emasys.model.Employee;
import com.esad.emasys.model.Position;
import com.esad.emasys.model.User;
import com.esad.emasys.services.impl.DepartmentService;
import com.esad.emasys.services.impl.EmployeeServiceImpl;
import com.esad.emasys.services.impl.PositionService;
import com.esad.emasys.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl empServies;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private UserService userService;

    /*
     * Display all employee with information
     * */
    @GetMapping("/employees")
    public String showEmployeesList(Model model) {
        List<Employee> empList = empServies.getAllEmployees();
        model.addAttribute("empList", empList);
        return "employees";
    }

    /*
     * Show add employee form
     * */
    @GetMapping("/employees/new")// this is button link name
    public String showAddEmployee(Model model) {
        Employee emp = new Employee();
        model.addAttribute("empNew", emp);//th:object
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("pageTitle", "Add Employee");

        return "new_employee"; //this is the html file name
    }

    /*
     * save emp button action from the Show add employee form
     * */
    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("empNew") Employee emp, RedirectAttributes redirect, Model model) {
        try {
            // Check if the user exists
            User user = userService.findByEmail(emp.getUser().getEmail());
            boolean isUpdate = emp.getId() != null;

            /*boolean isAllowSave = true;

            if (user == null) {
                if (emp.getUser().getId() != 0 && user.getId() == emp.getUser().getId()) {
                    isUpdate = true;
                }
            } else if (!isUpdate) {
                // Prevent saving new employee if email is already associated with another user
                model.addAttribute("pageTitle", "Add Employee");
                model.addAttribute("message", "Email " + emp.getUser().getEmail() + " already exists");
                model.addAttribute("flashType", "error");
                return "new_employee";
            }

            // Update user details if updating
            if (isUpdate) {
                if (user == null) {
                    user = new User();
                    user.setEmail(emp.getUser().getEmail());
                    user.setPassword("Google");
                    user.setRole(User.Role.EMPLOYEE);
                    userService.save(user);
                }
                emp.setUser(user);
            }*/

            /*User userNew = new User();
            userNew.setEmail(emp.getUser().getEmail());
            userNew.setPassword("Google");
            userNew.setRole(User.Role.EMPLOYEE);
            userService.save(userNew);

            emp.setUser(userNew);*/


            User existingUser = userService.findByEmail(emp.getUser().getEmail());

            if (existingUser != null) {
                // Update the existing user's information if needed

                userService.save(existingUser);
                emp.setUser(existingUser);
                empServies.saveEmployee(emp);
            } else {
                // Create new user
                User userNew = new User();
                userNew.setEmail(emp.getUser().getEmail());
                userNew.setPassword("Google");
                userNew.setRole(User.Role.EMPLOYEE);

                //save user info
                userService.save(userNew);

                //save employee info
                emp.setUser(userNew);
                empServies.saveEmployee(emp);
            }


            redirect.addFlashAttribute("message", "Employee " + emp.getFirstName() + " " + (isUpdate ? "Updated" : "Saved") + " Successfully");
            redirect.addFlashAttribute("flashType", "success");
            return "redirect:/employees";

        } catch (Exception e) {
            redirect.addFlashAttribute("message", "Save Error : " + e.getMessage());
            redirect.addFlashAttribute("flashType", "error");
            return "redirect:/employees";
        }
    }

    /*
     * Show add employee form as edit profile
     * */
    @GetMapping("/employees/edit/{id}")
    public String showEditEmployee(@PathVariable("id") Integer id, Model model) {
        try {
            Employee empNew = empServies.getEmployee(id); // Fetch employee by ID
            List<Department> departments = departmentService.getAllDepartments(); // Fetch all departments
            List<Position> positions = positionService.getAllPositions(); // Fetch all positions

            model.addAttribute("empNew", empNew); // Current employee
            model.addAttribute("departments", departments); // All departments
            model.addAttribute("positions", positions); // All positions
            model.addAttribute("pageTitle", "Edit Employee #" + id);
            return "new_employee";
        } catch (Exception e) {
            return "redirect:/employees";
        }
    }

    /*
     * Delete profile
     * */
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, RedirectAttributes redirect) {
        try {
            empServies.deleteEmployee(id);
            redirect.addFlashAttribute("message", "Employee #" + id + " has been deleted");
            redirect.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            redirect.addFlashAttribute("message", "Delete Error : " + e.getMessage());
            redirect.addFlashAttribute("flashType", "error");
        }
        return "redirect:/employees";
    }
}
