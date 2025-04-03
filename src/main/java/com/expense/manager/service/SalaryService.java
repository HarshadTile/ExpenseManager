package com.expense.manager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.expense.manager.model.Employee;
import com.expense.manager.model.EmployeeSalary;
import com.expense.manager.repository.EmployeeRepository;
import com.expense.manager.repository.EmployeeSalaryRepository;

@Service
public class SalaryService {

    @Autowired
    private EmployeeSalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    // Set or Update Employee Salary
    public void setSalary(Long employeeId, BigDecimal salaryAmount, LocalDate effectiveDate) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Find if there is already a salary for today
        Optional<EmployeeSalary> existingSalary = salaryRepository.findByEmployeeIdAndEffectiveDate(employeeId, effectiveDate);

        if (existingSalary.isPresent()) {
            // If salary exists for today, update it
            EmployeeSalary salary = existingSalary.get();
            salary.setSalaryAmount(salaryAmount);
            salaryRepository.save(salary);
            System.out.println("Updated salary for Employee: " + employee.getName() + " on " + effectiveDate);
        } else {
            // If no salary exists for today, insert a new one
            EmployeeSalary newSalary = new EmployeeSalary();
            newSalary.setEmployee(employee);
            newSalary.setEmployeeCode(employee.getEmployeeCode());
            newSalary.setSalaryAmount(salaryAmount);
            newSalary.setEffectiveDate(effectiveDate);
            newSalary.setCreatedAt(LocalDateTime.now()); // Set current timestamp
            salaryRepository.save(newSalary);
            System.out.println("Inserted new salary for Employee: " + employee.getName() + " on " + effectiveDate);
        }
    }

}
