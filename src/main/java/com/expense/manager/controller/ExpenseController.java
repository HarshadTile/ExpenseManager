package com.expense.manager.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.manager.model.Attendance;
import com.expense.manager.model.Employee;
import com.expense.manager.model.Expense;
import com.expense.manager.model.Item;
import com.expense.manager.model.WorkType;
import com.expense.manager.repository.EmployeeRepository;
import com.expense.manager.service.AttendanceService;
import com.expense.manager.service.EmployeeService;
import com.expense.manager.service.ExpenseService;
import com.expense.manager.service.ItemService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/details")
    public ResponseEntity<?> getEmployeeDetails(@RequestParam String employeeCode) {
        Employee emp = employeeService.findByCode(employeeCode);
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        Map<String, String> response = new HashMap<>();
        response.put("name", emp.getName());
        response.put("contact", emp.getMobileNumber());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/items/details")
    public ResponseEntity<?> getItemDetails(@RequestParam String itemCode) {
        Item item = itemService.findByCode(itemCode);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("name", item.getItemName());
        response.put("rate", item.getRate());
        return ResponseEntity.ok(response);
    }
    
    // Get Expenses for a Specific Employee
    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> getTodayExpensesByEmployee(@RequestParam String employeeCode) {
        List<Map<String, Object>> expenses = expenseService.getTodayExpensesByEmployee(employeeCode);
        return ResponseEntity.ok(expenses);
    }


    // ✅ API to save a new expense
    @PostMapping("/add")
    public String addExpense(
            @RequestParam String empCode, 
            @RequestParam String itemCode, 
            @RequestParam int quantity, 
            @RequestParam double amount) {

        Expense expense = new Expense(empCode, itemCode, quantity, amount);
        expenseService.saveExpense(expense);
        return "Expense Added Successfully!";
    }

    // ✅ API to get today's expenses for a specific employee
    @GetMapping("/today/{empCode}")
    public List<Expense> getTodaysExpensesForEmployee(@PathVariable String empCode) {
        return expenseService.getTodaysExpensesForEmployee(empCode);
    }

    // ✅ API to get all expenses for today
    @GetMapping("/today")
    public List<Expense> getTodaysExpenses() {
        return expenseService.getTodaysExpenses();
    }
}