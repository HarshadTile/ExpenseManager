package com.expense.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expense.manager.model.Employee;
import com.expense.manager.model.Expense;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("SELECT e FROM Employee e WHERE e.active = true")
	List<Employee> findByActiveTrue();

	boolean existsByEmployeeCode(String employeeCode);
	
	boolean existsByAadharNumber(String aadharNumber);
	
	Employee findByEmployeeCode(String employeeCode);
	
	
	
	
	
}
