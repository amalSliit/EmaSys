package com.esad.emasys.controller;

import com.esad.emasys.model.Employee;
import com.esad.emasys.services.impl.EmployeeServiceImpl;
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
        model.addAttribute("pageTitle", "Add Employee");

        return "new_employee"; //this is the html file name
    }

    /*
     * save emp button action from the Show add employee form
     * */
    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("empNew") Employee emp, RedirectAttributes redirect, Model model) {
        try {

            /*
             * First check email already saved or not
             * */
            if (empServies.emailExists(emp.getEmail())) {
                model.addAttribute("pageTitle", "Add Employee");
                redirect.addFlashAttribute("message", "Error: Email " + emp.getEmail() + " already exists");
                redirect.addFlashAttribute("flashType", "error");
                return "new_employee";
            } else {
                empServies.saveEmployee(emp);
                redirect.addFlashAttribute("message", "Employee " + emp.getFirstName() + " Saved Successfully");
                redirect.addFlashAttribute("flashType", "success");
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("message", "Save Error : " + e.getMessage());
            redirect.addFlashAttribute("flashType", "error");
        }
        return "redirect:/employees";
    }

    /*
     * Show add employee form as edit profile
     * */
    @GetMapping("/employees/edit/{id}")
    public String showEditEmployee(@PathVariable("id") Integer id, Model model) {
        try {
            Employee emp = empServies.getEmployee(id);
            model.addAttribute("empNew", emp);
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
