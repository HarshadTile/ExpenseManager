package com.expense.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.Expense;
import com.expense.manager.model.Item;
import com.expense.manager.repository.ExpenseRepository;
import com.expense.manager.repository.ItemRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private ItemRepository itemRepository;

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
    
    public List<Map<String, Object>> getTodayExpensesByEmployee(String empCode) {
        List<Expense> expenses = expenseRepository.findTodayExpensesByEmpCode(empCode);
        List<Map<String, Object>> expenseDetails = new ArrayList<>();

        for (Expense expense : expenses) {
            // Fetch the corresponding Item
            List<Item> items = itemRepository.findByItemCode(expense.getItemCode());
            Item item = (items != null && !items.isEmpty()) ? items.get(0) : null;

            Map<String, Object> expenseMap = new HashMap<>();
            expenseMap.put("id", expense.getId());
            expenseMap.put("empCode", expense.getEmpCode());
            expenseMap.put("itemCode", expense.getItemCode());
            expenseMap.put("quantity", expense.getQuantity());
            expenseMap.put("amount", expense.getAmount());

            if (item != null) {
                expenseMap.put("itemName", item.getItemName());
                expenseMap.put("itemRate", item.getRate());
            } else {
                expenseMap.put("itemName", "Unknown");
                expenseMap.put("itemRate", 0);
            }

            expenseDetails.add(expenseMap);
        }

        return expenseDetails;
    }




}
