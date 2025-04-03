package com.expense.manager.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expense.manager.model.Employee;
import com.expense.manager.model.WorkType;
import com.expense.manager.repository.EmployeeRepository;
import com.expense.manager.service.EmployeeService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    // Show Add Employee Form
    @GetMapping("/add-emp")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add_emp";
    }
    
    @GetMapping("/check-employee-id")
    public ResponseEntity<Boolean> checkEmployeeId(@RequestParam String employeeCode) {
        boolean exists = employeeRepository.existsByEmployeeCode(employeeCode);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/check-aadhar-number")
    public ResponseEntity<Boolean> checkAadharNumber(@RequestParam String aadharNumber) {
        boolean exists = employeeRepository.existsByAadharNumber(aadharNumber);
        return ResponseEntity.ok(exists);
    }
    // Save Employee
    @PostMapping("/save-emp")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.addEmployee(employee);
        if (employee.getWork() == null) {
            throw new IllegalArgumentException("Work type must be selected!");
        }
        return "/add-emp";
    }
    
    @GetMapping("/view-emp")
    public String viewEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "view_emp";
    }

    // Soft Delete Employee
    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.softDeleteEmployee(id);
        return "redirect:/view-emp";
    }
    @GetMapping("/update-employee/{id}")
    public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "upd_emp";
    }
    
    @PostMapping("/update-employee")
    public String updateEmployee(@ModelAttribute Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(employee.getId());

        // Ensure the Employee Code is not modified
        employee.setEmployeeCode(existingEmployee.getEmployeeCode());

        employeeService.updateEmployee(employee);
        return "redirect:/view-emp";
    }


}
