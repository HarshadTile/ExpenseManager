package com.expense.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.manager.model.Expense;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Get all expenses for a specific employee on a specific date
    List<Expense> findByEmpCodeAndTimestampBetween(String empCode, LocalDate startDate, LocalDate endDate);

    // Get all expenses for today
    List<Expense> findByTimestampBetween(LocalDate start, LocalDate end);
}
