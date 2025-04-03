package com.expense.manager.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import com.expense.manager.model.Employee;
import com.expense.manager.service.EmployeeService;
import com.expense.manager.service.SalaryService;

@Controller
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	private EmployeeService employeeService;
	 
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public String showSalaryPage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "salary";  // Redirects to salary.html
    }
    
    @PostMapping("/set")
    public ResponseEntity<String> setSalary(
            @RequestParam Long employeeId,
            @RequestParam BigDecimal salaryAmount,
            @RequestParam String effectiveDate) {

        System.out.println("Received Salary Update: Employee ID: " + employeeId 
                           + ", Salary: " + salaryAmount 
                           + ", Effective Date: " + effectiveDate);

        LocalDate date = LocalDate.parse(effectiveDate);
        salaryService.setSalary(employeeId, salaryAmount, date);
        return ResponseEntity.ok("Salary updated successfully");
    }

}
