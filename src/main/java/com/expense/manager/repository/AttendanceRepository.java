package com.expense.manager.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expense.manager.model.Attendance;
import com.expense.manager.model.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndDate(Employee employee, LocalDate date);
    void deleteByEmployeeAndDate(Employee employee, LocalDate date);
    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Attendance a WHERE a.employee.id = :employeeId AND a.date = :date")
    void deleteByEmployeeIdAndDate(@Param("employeeId") Long employeeId, @Param("date") LocalDate date);

    List<Attendance> findByDate(LocalDate date);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.date = :date AND a.status = :status")
    long countByDateAndStatus(@Param("date") LocalDate date, @Param("status") Attendance.Status status);

}
