package com.expense.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.Employee;
import com.expense.manager.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    // Add new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findByActiveTrue(); // Fetch only active employees
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void updateEmployee(Employee updatedEmployee) {
        employeeRepository.save(updatedEmployee);
    }

    
    // Update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setMobileNumber(updatedEmployee.getMobileNumber());
            employee.setLocalAddress(updatedEmployee.getLocalAddress());
            employee.setPermanentAddress(updatedEmployee.getPermanentAddress());
            employee.setAadharNumber(updatedEmployee.getAadharNumber());
            employee.setAge(updatedEmployee.getAge());
            employee.setEmployeeCode(updatedEmployee.getEmployeeCode());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void softDeleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setActive(false); // Mark employee as inactive
        employeeRepository.save(employee);
    }


}
