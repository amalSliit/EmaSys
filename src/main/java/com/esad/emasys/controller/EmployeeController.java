package com.esad.emasys.controller;

import com.esad.emasys.model.Department;
import com.esad.emasys.model.Employee;
import com.esad.emasys.model.LeaveBalance;
import com.esad.emasys.model.Position;
import com.esad.emasys.service.impl.EmployeeServiceImpl;
import com.esad.emasys.service.interfaces.DepartmentService;
import com.esad.emasys.service.interfaces.LeaveBalanceService;
import com.esad.emasys.service.interfaces.PositionService;
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
    private LeaveBalanceService lbService;


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

            boolean isNewEmail = false;
            boolean isUpdate = false;

            Employee existUser = empServies.getEmployee(emp.getEmail());


            if (emp.getId() != null) {
                //Update employee
                Employee existEmp = empServies.getEmployee(emp.getId());

                //Check emp email exist or not
                if (existUser != null) {
                    //having an email, so check it's beloing with tis user
                    if (existUser.getId() == existEmp.getId()) {
                        // yes email is belongs to update user
                        isUpdate = true;
                    } else {
                        //email already exist
                        // Prevent saving new employee if email is already associated with another user
                        model.addAttribute("pageTitle", "Edit Employee");
                        model.addAttribute("message", "Email " + emp.getEmail() + " already exists");
                        model.addAttribute("departments", departmentService.getAllDepartments());
                        model.addAttribute("positions", positionService.getAllPositions());
                        model.addAttribute("flashType", "error");
                        return "new_employee";
                    }
                } else {
                    //new email
                    isUpdate = true;
                }
            } else {
                isNewEmail = existUser == null;
            }


            if (isUpdate) {
                // Update the existing user's information if needed
                empServies.saveEmployee(emp);
            } else {
                if (isNewEmail) {
                    // Create new user
                    empServies.saveEmployee(emp);
                } else {
                    model.addAttribute("empNew", emp);
                    model.addAttribute("departments", departmentService.getAllDepartments());
                    model.addAttribute("positions", positionService.getAllPositions());
                    model.addAttribute("pageTitle", "Add Employee");
                    model.addAttribute("message", "Email " + emp.getEmail() + " already exists");
                    model.addAttribute("flashType", "error");
                    return "new_employee";
                }
            }


            // Create and save LeaveBalance for new employee
            if (emp.getId() != null && isNewEmail) { // Only for new employees
                LeaveBalance leaveBalance = new LeaveBalance();
                leaveBalance.setEmployee(emp);
                lbService.saveLeaveBalance(leaveBalance);
            }


            redirect.addFlashAttribute("message", "Employee " + emp.getFirstName() + " " + (isUpdate ? "Updated" : "Saved") + " Successfully");
            redirect.addFlashAttribute("flashType", "success");
            return "redirect:/employees";

        } catch (Exception e) {
            redirect.addFlashAttribute("message", "Save Error : " + e.toString());
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
