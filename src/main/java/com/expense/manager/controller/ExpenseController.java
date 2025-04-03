//package com.expense.manager.controller;
//
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.expense.manager.model.Attendance;
//import com.expense.manager.model.Employee;
//import com.expense.manager.model.WorkType;
//import com.expense.manager.repository.EmployeeRepository;
//import com.expense.manager.service.AttendanceService;
//import com.expense.manager.service.EmployeeService;
//
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//@RequestMapping("/expenses")
//public class ExpenseController {
//
//    @Autowired
//    private ExpenseService expenseService;
//
//    @PostMapping("/add")
//    @ResponseBody
//    public String addExpense(
//            @RequestParam String empCode, 
//            @RequestParam String itemCode, 
//            @RequestParam int quantity, 
//            @RequestParam double amount) {
//        
//        Expense expense = new Expense();
//        expense.setEmpCode(empCode);
//        expense.setItemCode(itemCode);
//        expense.setQuantity(quantity);
//        expense.setAmount(amount);
//        expense.setTimestamp(LocalDateTime.now());
//
//        expenseService.save(expense);
//        return "Expense Added Successfully!";
//    }
//}
