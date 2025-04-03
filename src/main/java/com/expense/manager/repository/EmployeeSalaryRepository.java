package com.expense.manager.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.manager.model.EmployeeSalary;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {

	Optional<EmployeeSalary> findByEmployeeIdAndEffectiveDate(Long employeeId, LocalDate effectiveDate);

}
