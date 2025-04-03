package com.expense.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.Expense;
import com.expense.manager.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Save a new expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getTodaysExpensesForEmployee(String empCode) {
        LocalDate today = LocalDate.now();
        return expenseRepository.findByEmpCodeAndTimestampBetween(empCode, today, today);
    }

    public List<Expense> getTodaysExpenses() {
        LocalDate today = LocalDate.now();
        return expenseRepository.findByTimestampBetween(today, today);
    }

}
